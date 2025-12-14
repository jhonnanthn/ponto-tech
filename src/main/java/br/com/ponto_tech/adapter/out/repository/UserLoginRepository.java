package br.com.ponto_tech.adapter.out.repository;

import br.com.ponto_tech.application.core.domain.entity.UserLogin;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Repository
public class UserLoginRepository {

    private final DynamoDbTable<UserLogin> table;

    public UserLoginRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.table = enhancedClient.table("UserLogins", TableSchema.fromBean(UserLogin.class));
    }

    public void save(UserLogin entity) {
        try {
            table.putItem(entity);
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public UserLogin findById(String username) {
        try {
            return table.getItem(r -> r.key(k -> k.partitionValue(username)));
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(String username) {
        try {
            table.deleteItem(r -> r.key(k -> k.partitionValue(username)));
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }
}

