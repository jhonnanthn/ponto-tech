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
public class Users {

    @Getter(onMethod_ = @__({@DynamoDbPartitionKey, @DynamoDbAttribute("userId")}))
    @Setter
    private String userId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("fullName")}))
    @Setter
    private String fullName;

    @Getter(onMethod_ = @__({@DynamoDbSecondaryPartitionKey(indexNames = "EmailIndex"), @DynamoDbAttribute("email")}))
    @Setter
    private String email;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("cpf")}))
    @Setter
    private String cpf;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("photoUrl")}))
    @Setter
    private String photoUrl;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("faceId")}))
    @Setter
    private String faceId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("role")}))
    @Setter
    private String role;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("createdAt")}))
    @Setter
    private String createdAt;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("updatedAt")}))
    @Setter
    private String updatedAt;
}