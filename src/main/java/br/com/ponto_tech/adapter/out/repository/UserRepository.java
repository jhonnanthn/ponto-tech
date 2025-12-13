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

    public void createUser() {
        try {
            // Criação do objeto Users
            Users user = new Users();
            user.setUserId("a1b2c3d4-5678-90ef-ghij-klmnopqrstuv"); // DEVE SER UM UUID OU OUTRO ID ÚNICO
            user.setFullName("John Doe");
            user.setEmail("john.doe@example.com");
            user.setCpf("123.456.789-10");
            user.setPhotoUrl("https://example.com/photo.jpg");
            user.setFaceId("face123");
            user.setRole("Employee");
            user.setCreatedAt("2025-12-13T01:29:00Z");
            user.setUpdatedAt("2025-12-13T01:29:00Z");

            // Inserindo no DynamoDB
            userTable.putItem(user);
            System.out.println("User created successfully!");

        } catch (DynamoDbException e) {
            System.err.println("Error while creating user: " + e.getMessage());
            throw e;
        }
    }

    public void save(Users users) {
        userTable.putItem(users);
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
        userTable.deleteItem(r -> r.key(k -> k.partitionValue(userId)));
    }

    public List<Users> findAll() {
        List<Users> results = new ArrayList<>();
        userTable.scan().items().forEach(results::add);
        return results;
    }
}