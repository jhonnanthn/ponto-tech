package br.com.ponto_tech.application.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OvertimeSummaryDTO {
    private String userId;
    private String month;
    private Integer totalOvertimeMinutes;
    private Double totalOvertimeHours;
}
