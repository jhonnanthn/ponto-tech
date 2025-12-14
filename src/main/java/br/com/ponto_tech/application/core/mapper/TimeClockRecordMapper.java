package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.entity.TimeClockRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeClockRecordMapper {
    TimeClockRecordMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(TimeClockRecordMapper.class);

    TimeClockRecord toEntity(TimeClockRecordDTO dto);
    TimeClockRecordDTO toDto(TimeClockRecord entity);
    List<TimeClockRecordDTO> toDtoList(List<TimeClockRecord> entities);
}

