package br.com.ponto_tech.adapter.out.repository;

import br.com.ponto_tech.application.core.domain.entity.Users;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final DynamoDbTable<Users> userTable;
    private final DynamoDbIndex<Users> emailIndex;

    public UserRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.userTable = enhancedClient.table("Users", TableSchema.fromBean(Users.class));
        // initialize the GSI index for email lookups
        this.emailIndex = this.userTable.index("EmailIndex");
    }

    public void save(Users users) {
        try {
            userTable.putItem(users);
        } catch (DynamoDbException e) {
            System.err.println("Error occurred: " + e.getMessage());
            throw e;
        }
    }

    public Users findById(String userId) {
        try {
            return userTable.getItem(r -> r.key(k -> k.partitionValue(userId)));
        } catch (DynamoDbException e) {
            System.err.println("Error occurred: " + e.getMessage());
            throw e;
        }
    }

    public Users findByEmail(String email) {
        try {
            // Query the EmailIndex GSI where the partition key is the email
            QueryConditional cond = QueryConditional.keyEqualTo(Key.builder().partitionValue(email).build());
            // iterate pages and return the first matching item (should be unique if email is unique)
            var pages = emailIndex.query(r -> r.queryConditional(cond));
            for (Page<Users> page : pages) {
                List<Users> items = page.items();
                if (items != null && !items.isEmpty()) {
                    return items.get(0);
                }
            }
            return null;
        } catch (DynamoDbException e) {
            System.err.println("Error occurred: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(String userId) {
        try {
            userTable.deleteItem(r -> r.key(k -> k.partitionValue(userId)));
        } catch (DynamoDbException e) {
            System.err.println("Error occurred: " + e.getMessage());
            throw e;
        }
    }

    public List<Users> findAll() {
        List<Users> results = new ArrayList<>();
        userTable.scan().items().forEach(results::add);
        return results;
    }
}