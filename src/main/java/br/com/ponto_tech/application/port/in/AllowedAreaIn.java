package br.com.ponto_tech.application.port.in;

import br.com.ponto_tech.application.core.domain.dto.AllowedAreaDTO;

import java.util.List;

public interface AllowedAreaIn {
    void save(AllowedAreaDTO dto);
    AllowedAreaDTO findById(String id);
    List<AllowedAreaDTO> findAll();
    void update(AllowedAreaDTO dto);
    void deleteById(String id);
}

