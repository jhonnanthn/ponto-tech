package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class DailyWorkSummaryDTO {
    private String userId;
    private String date;
    private String lastUpdate;
    private Integer overtimeMinutes;
    private Integer pointsCount;
    private Integer totalWorkedMinutes;
}
