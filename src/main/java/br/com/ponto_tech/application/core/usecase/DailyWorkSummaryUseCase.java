package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.DailyWorkSummaryRepository;
import br.com.ponto_tech.application.core.domain.dto.CreateDailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.dto.DailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.dto.OvertimeSummaryDTO;
import br.com.ponto_tech.application.core.domain.entity.DailyWorkSummary;
import br.com.ponto_tech.application.core.mapper.DailyWorkSummaryMapper;
import br.com.ponto_tech.application.port.in.DailyWorkSummaryIn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyWorkSummaryUseCase implements DailyWorkSummaryIn {

    private final DailyWorkSummaryRepository repository;
    private final DailyWorkSummaryMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public DailyWorkSummaryUseCase(DailyWorkSummaryRepository repository, DailyWorkSummaryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(CreateDailyWorkSummaryDTO dto) {
        DailyWorkSummary entity = mapper.toEntity(dto);
        entity.setLastUpdate(LocalDateTime.now().format(formatter));
        repository.save(entity);
    }

    @Override
    public DailyWorkSummaryDTO findByUserIdAndDate(String userId, String date) {
        DailyWorkSummary entity = repository.findByUserIdAndDate(userId, date);
        return entity == null ? null : mapper.toDto(entity);
    }

    @Override
    public List<DailyWorkSummaryDTO> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DailyWorkSummaryDTO> findByUserIdAndMonth(String userId, String month) {
        return repository.findByUserIdAndMonth(userId, month).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OvertimeSummaryDTO calculateMonthlyOvertime(String userId, String month) {
        List<DailyWorkSummary> summaries = repository.findByUserIdAndMonth(userId, month);
        
        int totalOvertimeMinutes = summaries.stream()
                .mapToInt(summary -> summary.getOvertimeMinutes() != null ? summary.getOvertimeMinutes() : 0)
                .sum();
        
        double totalOvertimeHours = totalOvertimeMinutes / 60.0;
        
        return new OvertimeSummaryDTO(userId, month, totalOvertimeMinutes, totalOvertimeHours);
    }

    @Override
    public void update(DailyWorkSummaryDTO dto) {
        DailyWorkSummary entity = mapper.toEntity(dto);
        entity.setLastUpdate(LocalDateTime.now().format(formatter));
        repository.save(entity);
    }

    @Override
    public void deleteByUserIdAndDate(String userId, String date) {
        repository.deleteByUserIdAndDate(userId, date);
    }
}
