package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.UserLoginDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateUserLoginDTO;
import br.com.ponto_tech.application.core.domain.entity.UserLogin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserLoginMapper {

    @Mapping(target = "passwordHash", ignore = true) // hashing handled in usecase/service
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now().toString())")
    UserLogin toEntity(CreateUserLoginDTO dto);

    UserLoginDTO toDto(UserLogin entity);
}

