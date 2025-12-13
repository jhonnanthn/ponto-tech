package br.com.ponto_tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import software.amazon.awssdk.regions.Region;

@SpringBootApplication
public class PontoTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(PontoTechApplication.class, args);

        // Configuração do cliente DynamoDB
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(Region.of("sa-east-1")) // Região configurada
                .build();

        try {
            // Testa a conexão listando as tabelas
            ListTablesRequest request = ListTablesRequest.builder().build();
            ListTablesResponse response = dynamoDbClient.listTables(request);

            System.out.println("Conexão bem-sucedida! Tabelas no DynamoDB:");
            response.tableNames().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao DynamoDB: " + e.getMessage());
            e.printStackTrace();
        } finally {
            dynamoDbClient.close();
        }
    }
}