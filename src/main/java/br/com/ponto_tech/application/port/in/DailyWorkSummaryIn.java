package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.dto.CreateDailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.dto.DailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.dto.OvertimeSummaryDTO;

import java.util.List;

public interface DailyWorkSummaryIn {
    void save(CreateDailyWorkSummaryDTO dto);
    DailyWorkSummaryDTO findByUserIdAndDate(String userId, String date);
    List<DailyWorkSummaryDTO> findAllByUserId(String userId);
    List<DailyWorkSummaryDTO> findByUserIdAndMonth(String userId, String month);
    OvertimeSummaryDTO calculateMonthlyOvertime(String userId, String month);
    void update(DailyWorkSummaryDTO dto);
    void deleteByUserIdAndDate(String userId, String date);
}
