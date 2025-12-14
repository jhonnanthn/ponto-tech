package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.AllowedAreaDTO;
import br.com.ponto_tech.application.core.domain.entity.AllowedArea;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AllowedAreaMapper {
    AllowedAreaMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(AllowedAreaMapper.class);

    AllowedArea toEntity(AllowedAreaDTO dto);
    AllowedAreaDTO toDto(AllowedArea entity);
    List<AllowedAreaDTO> toDtoList(List<AllowedArea> entities);
}

