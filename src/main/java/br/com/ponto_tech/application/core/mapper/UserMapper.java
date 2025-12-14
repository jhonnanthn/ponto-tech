package br.com.ponto_tech.application.core.mapper;

import br.com.ponto_tech.application.core.domain.dto.UserDTO;
import br.com.ponto_tech.application.core.domain.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toUser(UserDTO userDTO);
    UserDTO toDto(Users user);
    List<UserDTO> toDtoList(List<Users> users);
}
