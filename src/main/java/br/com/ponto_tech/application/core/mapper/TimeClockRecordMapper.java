package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateTimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.entity.TimeClockRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeClockRecordMapper {

    @Mapping(target = "recordId", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now().toString())")
    TimeClockRecord toEntity(CreateTimeClockRecordDTO dto);

    TimeClockRecord toEntity(TimeClockRecordDTO dto);
    TimeClockRecordDTO toDto(TimeClockRecord entity);
    List<TimeClockRecordDTO> toDtoList(List<TimeClockRecord> entities);
}
