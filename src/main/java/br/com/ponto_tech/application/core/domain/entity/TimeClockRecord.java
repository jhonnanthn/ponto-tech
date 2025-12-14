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
public class TimeClockRecord {

    @Getter(onMethod_ = @__({@DynamoDbPartitionKey, @DynamoDbAttribute("recordId")}))
    @Setter
    private String recordId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("userId")}))
    @Setter
    private String userId;

    @Getter(onMethod_ = @__({@DynamoDbSortKey, @DynamoDbAttribute("timestamp")}))
    @Setter
    private String timestamp;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("location")}))
    @Setter
    private String location;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("deviceId")}))
    @Setter
    private String deviceId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("recognizedFaceId")}))
    @Setter
    private String recognizedFaceId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("status")}))
    @Setter
    private String status;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("createdAt")}))
    @Setter
    private String createdAt;
}