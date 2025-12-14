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

    @GetMapping("/user/{userId}")
    @Operation(summary = "Listar todos os registros de ponto por usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de registros",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeClockRecordDTO.class)))
    })
    public List<TimeClockRecordDTO> findAll(@PathVariable("userId") String userId) {
        return timeClockRecordIn.findAllByUserId(userId);
    }

    @GetMapping("/user/{userId}/by-date")
    @Operation(summary = "Listar registros de ponto por usuário e data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de registros filtrados por data",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeClockRecordDTO.class)))
    })
    public List<TimeClockRecordDTO> findByDate(@PathVariable("userId") String userId, @RequestParam("date") String date) {
        return timeClockRecordIn.findByUserIdAndDate(userId, date);
    }

    @GetMapping("/user/{userId}/month/{month}")
    @Operation(summary = "Listar registros de ponto por usuário e mês")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de registros filtrados por mês",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeClockRecordDTO.class)))
    })
    public List<TimeClockRecordDTO> findByUserIdAndMonth(
            @PathVariable("userId") String userId,
            @PathVariable("month") String month) {
        return timeClockRecordIn.findByUserIdAndMonth(userId, month);
    }

    @GetMapping("/user/{userId}/{id}")
    @Operation(summary = "Buscar registro por usuário e ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeClockRecordDTO.class))),
            @ApiResponse(responseCode = "404", description = "Registro nao encontrado")
    })
    public ResponseEntity<TimeClockRecordDTO> findById(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        TimeClockRecordDTO dto = timeClockRecordIn.findByUserIdAndId(userId, id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Criar um novo registro de ponto")
    public ResponseEntity<Void> create(@RequestBody CreateTimeClockRecordDTO dto) {
        String recordId = timeClockRecordIn.save(dto);
        return ResponseEntity.created(URI.create("/v1/time-clock-records/" + recordId)).build();
    }

    @PutMapping("/user/{userId}/{id}")
    @Operation(summary = "Atualizar registro de ponto")
    public ResponseEntity<Void> update(@PathVariable("userId") String userId, @PathVariable("id") String id, @RequestBody TimeClockRecordDTO dto) {
        TimeClockRecordDTO existing = timeClockRecordIn.findByUserIdAndId(userId, id);
        if (existing == null) return ResponseEntity.notFound().build();
        if (dto.getRecordId() == null || !dto.getRecordId().equals(id)) dto.setRecordId(id);
        if (dto.getUserId() == null || !dto.getUserId().equals(userId)) dto.setUserId(userId);
        timeClockRecordIn.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}/{id}")
    @Operation(summary = "Deletar registro de ponto por usuário e id")
    public ResponseEntity<Void> delete(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        TimeClockRecordDTO existing = timeClockRecordIn.findByUserIdAndId(userId, id);
        if (existing == null) return ResponseEntity.notFound().build();
        timeClockRecordIn.deleteByUserIdAndId(userId, id);
        return ResponseEntity.noContent().build();
    }
}
