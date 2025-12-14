package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.UserDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateUserDTO;
import br.com.ponto_tech.application.core.domain.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userId", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now().toString())")
    Users toUser(CreateUserDTO dto);

    Users toUser(UserDTO userDTO);
    UserDTO toDto(Users user);
    List<UserDTO> toDtoList(List<Users> users);
}
