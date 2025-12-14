package br.com.ponto_tech.application.core.usecase;

import br.com.ponto_tech.adapter.out.repository.AllowedAreaRepository;
import br.com.ponto_tech.application.core.domain.dto.AllowedAreaDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateAllowedAreaDTO;
import br.com.ponto_tech.application.core.domain.entity.AllowedArea;
import br.com.ponto_tech.application.core.mapper.AllowedAreaMapper;
import br.com.ponto_tech.application.port.in.AllowedAreaIn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllowedAreaUseCase implements AllowedAreaIn {

    private final AllowedAreaRepository repository;
    private final AllowedAreaMapper mapper;

    public AllowedAreaUseCase(AllowedAreaRepository repository, AllowedAreaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public String save(CreateAllowedAreaDTO dto) {
        AllowedArea entity = mapper.toEntity(dto);
        repository.save(entity);
        return entity.getAreaId();
    }

    @Override
    public AllowedAreaDTO findById(String id) {
        AllowedArea entity = repository.findById(id);
        return entity == null ? null : mapper.toDto(entity);
    }

    @Override
    public List<AllowedAreaDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void update(AllowedAreaDTO dto) {
        AllowedArea entity = new AllowedArea();
        entity.setAreaId(dto.getAreaId());
        entity.setName(dto.getName());
        entity.setCoordinates(dto.getCoordinates());
        entity.setRadius(dto.getRadius());
        entity.setCreatedAt(dto.getCreatedAt());
        repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
