package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.AllowedAreaRepository;
import br.com.ponto_tech.application.core.domain.dto.AllowedAreaDTO;
import br.com.ponto_tech.application.core.domain.entity.AllowedArea;
import br.com.ponto_tech.application.core.mapper.AllowedAreaMapper;
import br.com.ponto_tech.application.port.in.AllowedAreaIn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllowedAreaUseCase implements AllowedAreaIn {

    private final AllowedAreaRepository repository;

    public AllowedAreaUseCase(AllowedAreaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(AllowedAreaDTO dto) {
        repository.save(AllowedAreaMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public AllowedAreaDTO findById(String id) {
        return AllowedAreaMapper.INSTANCE.toDto(repository.findById(id));
    }

    @Override
    public List<AllowedAreaDTO> findAll() {
        return AllowedAreaMapper.INSTANCE.toDtoList(repository.findAll());
    }

    @Override
    public void update(AllowedAreaDTO dto) {
        repository.save(AllowedAreaMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

