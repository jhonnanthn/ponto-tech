package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.CreateDailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.dto.DailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.entity.DailyWorkSummary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyWorkSummaryMapper {
    DailyWorkSummaryDTO toDto(DailyWorkSummary entity);
    DailyWorkSummary toEntity(DailyWorkSummaryDTO dto);
    DailyWorkSummary toEntity(CreateDailyWorkSummaryDTO dto);
}
