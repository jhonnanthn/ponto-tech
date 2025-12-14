package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String fullName;
    private String email;
    private String cpf;
    private String photoUrl;
    private String faceId;
    private String role;
    private String createdAt;
    private String updatedAt;
}
