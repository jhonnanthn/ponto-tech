package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.dto.TimeClockRecordDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateTimeClockRecordDTO;
import br.com.ponto_tech.application.port.in.TimeClockRecordIn;
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
@RequestMapping("/v1/time-clock-records")
@Tag(name = "TimeClockRecords", description = "Endpoints para gerenciar registros de ponto")
public class TimeClockRecordController {

    @Autowired
    private TimeClockRecordIn timeClockRecordIn;

    @GetMapping
    @Operation(summary = "Listar todos os registros de ponto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de registros",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeClockRecordDTO.class)))
    })
    public List<TimeClockRecordDTO> findAll() {
        return timeClockRecordIn.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar registro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeClockRecordDTO.class))),
            @ApiResponse(responseCode = "404", description = "Registro nao encontrado")
    })
    public ResponseEntity<TimeClockRecordDTO> findById(@PathVariable("id") String id) {
        TimeClockRecordDTO dto = timeClockRecordIn.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Criar um novo registro de ponto")
    public ResponseEntity<Void> create(@RequestBody CreateTimeClockRecordDTO dto) {
        String recordId = timeClockRecordIn.save(dto);
        return ResponseEntity.created(URI.create("/v1/time-clock-records/" + recordId)).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar registro de ponto")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody TimeClockRecordDTO dto) {
        TimeClockRecordDTO existing = timeClockRecordIn.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        if (dto.getRecordId() == null || !dto.getRecordId().equals(id)) dto.setRecordId(id);
        timeClockRecordIn.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar registro de ponto por id")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        TimeClockRecordDTO existing = timeClockRecordIn.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        timeClockRecordIn.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
