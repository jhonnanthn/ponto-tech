package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.AllowedAreaDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateAllowedAreaDTO;
import br.com.ponto_tech.application.core.domain.entity.AllowedArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AllowedAreaMapper {

    @Mapping(target = "areaId", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now().toString())")
    AllowedArea toEntity(CreateAllowedAreaDTO dto);

    AllowedAreaDTO toDto(AllowedArea entity);
    List<AllowedAreaDTO> toDtoList(List<AllowedArea> entities);
}
