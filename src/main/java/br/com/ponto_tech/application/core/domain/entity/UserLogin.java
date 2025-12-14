package br.com.ponto_tech.application.core.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
public class UserLogin {

    @Getter(onMethod_ = @__({@DynamoDbPartitionKey, @DynamoDbAttribute("username")}))
    @Setter
    private String username;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("passwordHash")}))
    @Setter
    private String passwordHash;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("status")}))
    @Setter
    private String status;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("createdAt")}))
    @Setter
    private String createdAt;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("updatedAt")}))
    @Setter
    private String updatedAt;
}

