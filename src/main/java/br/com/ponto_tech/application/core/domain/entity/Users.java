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

    @Getter(onMethod_ = @__({@DynamoDbPartitionKey, @DynamoDbAttribute("UserID")}))
    @Setter
    private String userId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("FullName")}))
    @Setter
    private String fullName;

    @Getter(onMethod_ = @__({@DynamoDbSecondaryPartitionKey(indexNames = "EmailIndex"), @DynamoDbAttribute("Email")}))
    @Setter
    private String email;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("CPF")}))
    @Setter
    private String cpf;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("PhotoURL")}))
    @Setter
    private String photoUrl;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("FaceId")}))
    @Setter
    private String faceId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("Role")}))
    @Setter
    private String role;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("CreatedAt")}))
    @Setter
    private String createdAt;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("UpdatedAt")}))
    @Setter
    private String updatedAt;
}