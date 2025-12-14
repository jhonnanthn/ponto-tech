package br.com.ponto_tech.adapter.out.repository;

import br.com.ponto_tech.application.core.domain.entity.TimeClockRecord;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeClockRecordRepository {

    private final DynamoDbTable<TimeClockRecord> table;

    public TimeClockRecordRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.table = enhancedClient.table("TimeClockRecords", TableSchema.fromBean(TimeClockRecord.class));
    }

    public void save(TimeClockRecord entity) {
        try {
            table.putItem(entity);
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public TimeClockRecord findByUserIdAndId(String userId, String id) {
        try {
            // Query pela partition key e filtra pelo userId
            return table.query(r -> r.queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(id).build())))
                    .items()
                    .stream()
                    .filter(record -> userId.equals(record.getUserId()))
                    .findFirst()
                    .orElse(null);
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public void deleteByUserIdAndId(String userId, String id) {
        try {
            // Busca o item primeiro para obter o timestamp
            TimeClockRecord record = findByUserIdAndId(userId, id);
            if (record != null) {
                table.deleteItem(r -> r.key(k -> k.partitionValue(id).sortValue(record.getTimestamp())));
            }
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public List<TimeClockRecord> findAllByUserId(String userId) {
        List<TimeClockRecord> results = new ArrayList<>();
        table.scan().items().forEach(record -> {
            if (userId.equals(record.getUserId())) {
                results.add(record);
            }
        });
        return results;
    }

    public List<TimeClockRecord> findByUserIdAndDate(String userId, String date) {
        List<TimeClockRecord> results = new ArrayList<>();
        table.scan().items().forEach(record -> {
            if (userId.equals(record.getUserId()) && 
                record.getCreatedAt() != null && 
                record.getCreatedAt().startsWith(date)) {
                results.add(record);
            }
        });
        return results;
    }

    public List<TimeClockRecord> findByUserIdAndMonth(String userId, String month) {
        List<TimeClockRecord> results = new ArrayList<>();
        table.scan().items().forEach(record -> {
            if (userId.equals(record.getUserId()) && 
                record.getCreatedAt() != null && 
                record.getCreatedAt().startsWith(month)) {
                results.add(record);
            }
        });
        return results;
    }
}

