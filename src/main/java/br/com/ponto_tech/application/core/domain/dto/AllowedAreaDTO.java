package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class AllowedAreaDTO {
    private String areaId;
    private String name;
    private String coordinates;
    private double radius;
    private String createdAt;
}

