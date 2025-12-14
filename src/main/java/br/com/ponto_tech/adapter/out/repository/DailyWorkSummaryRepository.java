package br.com.ponto_tech.adapter.out.repository;

import br.com.ponto_tech.application.core.domain.entity.DailyWorkSummary;
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
public class DailyWorkSummaryRepository {

    private final DynamoDbTable<DailyWorkSummary> table;

    public DailyWorkSummaryRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.table = enhancedClient.table("DailyWorkSummaries", TableSchema.fromBean(DailyWorkSummary.class));
    }

    public void save(DailyWorkSummary entity) {
        try {
            table.putItem(entity);
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public DailyWorkSummary findByUserIdAndDate(String userId, String date) {
        try {
            return table.getItem(r -> r.key(k -> k.partitionValue(userId).sortValue(date)));
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public List<DailyWorkSummary> findAllByUserId(String userId) {
        try {
            List<DailyWorkSummary> results = new ArrayList<>();
            table.query(r -> r.queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(userId).build())))
                    .items()
                    .forEach(results::add);
            return results;
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public List<DailyWorkSummary> findByUserIdAndMonth(String userId, String month) {
        try {
            List<DailyWorkSummary> results = new ArrayList<>();
            table.query(r -> r.queryConditional(QueryConditional.keyEqualTo(Key.builder().partitionValue(userId).build())))
                    .items()
                    .forEach(summary -> {
                        if (summary.getDate() != null && summary.getDate().startsWith(month)) {
                            results.add(summary);
                        }
                    });
            return results;
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public void deleteByUserIdAndDate(String userId, String date) {
        try {
            table.deleteItem(r -> r.key(k -> k.partitionValue(userId).sortValue(date)));
        } catch (DynamoDbException e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public List<DailyWorkSummary> findAll() {
        List<DailyWorkSummary> results = new ArrayList<>();
        table.scan().items().forEach(results::add);
        return results;
    }
}
