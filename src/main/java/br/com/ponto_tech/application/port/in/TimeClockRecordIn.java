package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateTimeClockRecordDTO;

import java.util.List;

public interface TimeClockRecordIn {
    String save(CreateTimeClockRecordDTO dto);
    TimeClockRecordDTO findByUserIdAndId(String userId, String id);
    List<TimeClockRecordDTO> findAllByUserId(String userId);
    List<TimeClockRecordDTO> findByUserIdAndDate(String userId, String date);
    List<TimeClockRecordDTO> findByUserIdAndMonth(String userId, String month);
    void update(TimeClockRecordDTO dto);
    void deleteByUserIdAndId(String userId, String id);
}
