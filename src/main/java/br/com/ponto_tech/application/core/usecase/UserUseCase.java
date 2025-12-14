package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.UserRepository;
import br.com.ponto_tech.application.core.domain.dto.UserDTO;
import br.com.ponto_tech.application.core.domain.entity.Users;
import br.com.ponto_tech.application.core.mapper.UserMapper;
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
    public void save(UserDTO user) {
        userRepository.save(UserMapper.INSTANCE.toUser(user));
    }

    @Override
    public UserDTO findById(String id) {
        return UserMapper.INSTANCE.toDto(userRepository.findById(id));
    }

    @Override
    public List<UserDTO> findAll() {
        return UserMapper.INSTANCE.toDtoList(userRepository.findAll());
    }

    @Override
    public void update(UserDTO user) {
        userRepository.save(UserMapper.INSTANCE.toUser(user));
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
