package br.com.ponto_tech.adapter.out.repository;

import br.com.ponto_tech.application.core.domain.entity.Users;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final DynamoDbTable<Users> userTable;

    public UserRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.userTable = enhancedClient.table("Users", TableSchema.fromBean(Users.class));
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