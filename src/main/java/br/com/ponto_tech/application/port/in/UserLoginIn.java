package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.dto.UserLoginDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateUserLoginDTO;

public interface UserLoginIn {
    String save(CreateUserLoginDTO dto);
    UserLoginDTO findByUsername(String username);
    void deleteByUsername(String username);
}

