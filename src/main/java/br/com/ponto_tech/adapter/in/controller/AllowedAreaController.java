package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.dto.AllowedAreaDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateAllowedAreaDTO;
import br.com.ponto_tech.application.port.in.AllowedAreaIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/allowed-areas")
@Tag(name = "AllowedAreas", description = "Endpoints para gerenciar areas permitidas")
public class AllowedAreaController {

    @Autowired
    private AllowedAreaIn allowedAreaIn;

    @GetMapping
    @Operation(summary = "Listar todas as areas permitidas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de areas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllowedAreaDTO.class)))
    })
    public List<AllowedAreaDTO> findAll() {
        return allowedAreaIn.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar area por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Area encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllowedAreaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Area nao encontrada")
    })
    public ResponseEntity<AllowedAreaDTO> findById(@PathVariable("id") String id) {
        AllowedAreaDTO dto = allowedAreaIn.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Criar uma nova area permitida")
    public ResponseEntity<Void> create(@RequestBody CreateAllowedAreaDTO dto) {
        String areaId = allowedAreaIn.save(dto);
        return ResponseEntity.created(URI.create("/v1/allowed-areas/" + areaId)).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar area permitida")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody AllowedAreaDTO dto) {
        AllowedAreaDTO existing = allowedAreaIn.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        if (dto.getAreaId() == null || !dto.getAreaId().equals(id)) dto.setAreaId(id);
        allowedAreaIn.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar area permitida por id")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        AllowedAreaDTO existing = allowedAreaIn.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        allowedAreaIn.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
