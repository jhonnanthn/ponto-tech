package br.com.ponto_tech.application.core.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Data
@DynamoDbBean
public class DailyWorkSummary {

    @Getter(onMethod_ = @__({@DynamoDbPartitionKey, @DynamoDbAttribute("userId")}))
    @Setter
    private String userId;

    @Getter(onMethod_ = @__({@DynamoDbSortKey, @DynamoDbAttribute("date")}))
    @Setter
    private String date;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("lastUpdate")}))
    @Setter
    private String lastUpdate;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("overtimeMinutes")}))
    @Setter
    private Integer overtimeMinutes;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("pointsCount")}))
    @Setter
    private Integer pointsCount;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("totalWorkedMinutes")}))
    @Setter
    private Integer totalWorkedMinutes;
}
