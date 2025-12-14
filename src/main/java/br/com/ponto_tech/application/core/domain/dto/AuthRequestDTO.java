package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;
}

