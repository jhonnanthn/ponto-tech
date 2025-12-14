package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.dto.UserDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateUserDTO;

import java.util.List;

public interface UserIn {
    String save(CreateUserDTO user);
    UserDTO findById(String id);
    List<UserDTO> findAll();
    void update(UserDTO user);
    void deleteById(String id);
}
