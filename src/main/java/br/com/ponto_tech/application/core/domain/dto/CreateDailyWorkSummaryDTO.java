package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class CreateDailyWorkSummaryDTO {
    private String userId;
    private String date;
    private Integer overtimeMinutes;
    private Integer pointsCount;
    private Integer totalWorkedMinutes;
}
