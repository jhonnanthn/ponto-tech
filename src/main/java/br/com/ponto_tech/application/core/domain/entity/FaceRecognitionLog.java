package br.com.ponto_tech.application.core.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@Data
@DynamoDbBean
public class FaceRecognitionLog {

    @Getter(onMethod_ = @__({@DynamoDbPartitionKey, @DynamoDbAttribute("logId")}))
    @Setter
    private String logId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("userId")}))
    @Setter
    private String userId;

    @Getter(onMethod_ = @__({@DynamoDbSecondaryPartitionKey(indexNames = "StatusIndex"), @DynamoDbAttribute("status")}))
    @Setter
    private String status;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("errorDetail")}))
    @Setter
    private String errorDetail;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("createdAt")}))
    @Setter
    private String createdAt;
}