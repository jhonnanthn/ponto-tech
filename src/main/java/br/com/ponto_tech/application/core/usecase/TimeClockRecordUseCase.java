package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.TimeClockRecordRepository;
import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.entity.TimeClockRecord;
import br.com.ponto_tech.application.core.mapper.TimeClockRecordMapper;
import br.com.ponto_tech.application.port.in.TimeClockRecordIn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeClockRecordUseCase implements TimeClockRecordIn {

    private final TimeClockRecordRepository repository;

    public TimeClockRecordUseCase(TimeClockRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(TimeClockRecordDTO dto) {
        repository.save(TimeClockRecordMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public TimeClockRecordDTO findById(String id) {
        return TimeClockRecordMapper.INSTANCE.toDto(repository.findById(id));
    }

    @Override
    public List<TimeClockRecordDTO> findAll() {
        return TimeClockRecordMapper.INSTANCE.toDtoList(repository.findAll());
    }

    @Override
    public void update(TimeClockRecordDTO dto) {
        repository.save(TimeClockRecordMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

