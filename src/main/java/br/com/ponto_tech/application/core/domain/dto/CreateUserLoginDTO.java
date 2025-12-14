package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class CreateUserLoginDTO {
    private String username;
    private String password; // plain password in request
    private String status;
}

