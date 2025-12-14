package br.com.ponto_tech.adapter.out.repository;

import br.com.ponto_tech.application.core.domain.entity.AllowedArea;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AllowedAreaRepository {

    private final DynamoDbTable<AllowedArea> table;

    public AllowedAreaRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.table = enhancedClient.table("AllowedAreas", TableSchema.fromBean(AllowedArea.class));
    }

    public void save(AllowedArea entity) {
        try {
            table.putItem(entity);
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public AllowedArea findById(String id) {
        try {
            return table.getItem(r -> r.key(k -> k.partitionValue(id)));
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public void deleteById(String id) {
        try {
            table.deleteItem(r -> r.key(k -> k.partitionValue(id)));
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public List<AllowedArea> findAll() {
        List<AllowedArea> results = new ArrayList<>();
        table.scan().items().forEach(results::add);
        return results;
    }
}

