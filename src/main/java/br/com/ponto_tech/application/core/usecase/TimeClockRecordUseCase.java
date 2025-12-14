package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.TimeClockRecordRepository;
import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateTimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.entity.TimeClockRecord;
import br.com.ponto_tech.application.core.mapper.TimeClockRecordMapper;
import br.com.ponto_tech.application.port.in.TimeClockRecordIn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeClockRecordUseCase implements TimeClockRecordIn {

    private final TimeClockRecordRepository repository;
    private final TimeClockRecordMapper mapper;

    public TimeClockRecordUseCase(TimeClockRecordRepository repository, TimeClockRecordMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public String save(CreateTimeClockRecordDTO dto) {
        TimeClockRecord entity = mapper.toEntity(dto);
        repository.save(entity);
        return entity.getRecordId();
    }

    @Override
    public TimeClockRecordDTO findByUserIdAndId(String userId, String id) {
        TimeClockRecord entity = repository.findByUserIdAndId(userId, id);
        return entity == null ? null : mapper.toDto(entity);
    }

    @Override
    public List<TimeClockRecordDTO> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TimeClockRecordDTO> findByUserIdAndDate(String userId, String date) {
        return repository.findByUserIdAndDate(userId, date).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TimeClockRecordDTO> findByUserIdAndMonth(String userId, String month) {
        return repository.findByUserIdAndMonth(userId, month).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void update(TimeClockRecordDTO dto) {
        TimeClockRecord entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    public void deleteByUserIdAndId(String userId, String id) {
        repository.deleteByUserIdAndId(userId, id);
    }
}
