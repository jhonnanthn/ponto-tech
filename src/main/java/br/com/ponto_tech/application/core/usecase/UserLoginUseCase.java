package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.UserLoginRepository;
import br.com.ponto_tech.application.core.domain.dto.UserLoginDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateUserLoginDTO;
import br.com.ponto_tech.application.core.domain.entity.UserLogin;
import br.com.ponto_tech.application.core.mapper.UserLoginMapper;
import br.com.ponto_tech.application.port.in.UserLoginIn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginUseCase implements UserLoginIn {

    private final UserLoginRepository repository;
    private final UserLoginMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserLoginUseCase(UserLoginRepository repository, UserLoginMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String save(CreateUserLoginDTO dto) {
        UserLogin entity = mapper.toEntity(dto);
        // hash password
        entity.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        repository.save(entity);
        return entity.getUsername();
    }

    @Override
    public UserLoginDTO findByUsername(String username) {
        UserLogin entity = repository.findById(username);
        return entity == null ? null : mapper.toDto(entity);
    }

    @Override
    public void deleteByUsername(String username) {
        repository.deleteById(username);
    }
}

