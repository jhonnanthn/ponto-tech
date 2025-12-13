package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.entity.Users;

import java.util.List;

public interface UserIn {
    void save(Users users);
    Users findById(String id);
    List<Users> findAll();
    void update(Users users);
    void deleteById(String id);
}
