// Updated to Lombok + DynamoDB enhanced annotation style
package br.com.ponto_tech.application.core.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
public class AllowedArea {

    @Getter(onMethod_ = @__({@DynamoDbPartitionKey, @DynamoDbAttribute("areaId")}))
    @Setter
    private String areaId;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("name")}))
    @Setter
    private String name;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("coordinates")}))
    @Setter
    private String coordinates;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("radius")}))
    @Setter
    private double radius;

    @Getter(onMethod_ = @__({@DynamoDbAttribute("createdAt")}))
    @Setter
    private String createdAt;
}