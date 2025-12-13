package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.UserRepository;
import br.com.ponto_tech.application.core.domain.entity.Users;
import br.com.ponto_tech.application.port.in.UserIn;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserUseCase implements UserIn {

    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(Users users) {
        userRepository.save(users);
    }

    @Override
    public Users findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(Users users) {
        userRepository.save(users);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
