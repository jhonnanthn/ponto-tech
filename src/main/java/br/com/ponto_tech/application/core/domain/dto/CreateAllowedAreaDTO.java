package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class CreateAllowedAreaDTO {
    private String name;
    private String coordinates;
    private double radius;
}

