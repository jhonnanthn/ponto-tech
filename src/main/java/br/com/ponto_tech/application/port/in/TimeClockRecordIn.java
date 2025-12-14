package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;

import java.util.List;

public interface TimeClockRecordIn {
    void save(TimeClockRecordDTO dto);
    TimeClockRecordDTO findById(String id);
    List<TimeClockRecordDTO> findAll();
    void update(TimeClockRecordDTO dto);
    void deleteById(String id);
}

