package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateTimeClockRecordDTO;

import java.util.List;

public interface TimeClockRecordIn {
    String save(CreateTimeClockRecordDTO dto);
    TimeClockRecordDTO findById(String id);
    List<TimeClockRecordDTO> findAll();
    void update(TimeClockRecordDTO dto);
    void deleteById(String id);
}
