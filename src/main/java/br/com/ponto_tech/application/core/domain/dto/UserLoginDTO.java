package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String passwordHash;
    private String status;
    private String createdAt;
    private String updatedAt;
}

