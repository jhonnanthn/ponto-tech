package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.dto.CreateDailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.dto.DailyWorkSummaryDTO;
import br.com.ponto_tech.application.core.domain.dto.OvertimeSummaryDTO;
import br.com.ponto_tech.application.port.in.DailyWorkSummaryIn;
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

@RestController
@RequestMapping("/v1/daily-work-summaries")
@Tag(name = "DailyWorkSummaries", description = "Endpoints para gerenciar resumos diários de trabalho")
public class DailyWorkSummaryController {

    @Autowired
    private DailyWorkSummaryIn dailyWorkSummaryIn;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Listar todos os resumos diários de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de resumos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DailyWorkSummaryDTO.class)))
    })
    public List<DailyWorkSummaryDTO> findAllByUserId(@PathVariable("userId") String userId) {
        return dailyWorkSummaryIn.findAllByUserId(userId);
    }

    @GetMapping("/user/{userId}/month/{month}")
    @Operation(summary = "Listar resumos diários de um usuário por mês")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de resumos do mês",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DailyWorkSummaryDTO.class)))
    })
    public List<DailyWorkSummaryDTO> findByUserIdAndMonth(
            @PathVariable("userId") String userId,
            @PathVariable("month") String month) {
        return dailyWorkSummaryIn.findByUserIdAndMonth(userId, month);
    }

    @GetMapping("/user/{userId}/month/{month}/overtime")
    @Operation(summary = "Calcular total de horas extras de um usuário no mês")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cálculo de horas extras do mês",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OvertimeSummaryDTO.class)))
    })
    public ResponseEntity<OvertimeSummaryDTO> calculateMonthlyOvertime(
            @PathVariable("userId") String userId,
            @PathVariable("month") String month) {
        OvertimeSummaryDTO overtime = dailyWorkSummaryIn.calculateMonthlyOvertime(userId, month);
        return ResponseEntity.ok(overtime);
    }

    @GetMapping("/user/{userId}/date/{date}")
    @Operation(summary = "Buscar resumo diário por usuário e data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resumo encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DailyWorkSummaryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Resumo não encontrado")
    })
    public ResponseEntity<DailyWorkSummaryDTO> findByUserIdAndDate(
            @PathVariable("userId") String userId,
            @PathVariable("date") String date) {
        DailyWorkSummaryDTO dto = dailyWorkSummaryIn.findByUserIdAndDate(userId, date);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Criar um novo resumo diário")
    public ResponseEntity<Void> create(@RequestBody CreateDailyWorkSummaryDTO dto) {
        dailyWorkSummaryIn.save(dto);
        return ResponseEntity.created(
                URI.create("/v1/daily-work-summaries/user/" + dto.getUserId() + "/date/" + dto.getDate())
        ).build();
    }

    @PutMapping("/user/{userId}/date/{date}")
    @Operation(summary = "Atualizar resumo diário")
    public ResponseEntity<Void> update(
            @PathVariable("userId") String userId,
            @PathVariable("date") String date,
            @RequestBody DailyWorkSummaryDTO dto) {
        DailyWorkSummaryDTO existing = dailyWorkSummaryIn.findByUserIdAndDate(userId, date);
        if (existing == null) return ResponseEntity.notFound().build();
        
        if (dto.getUserId() == null || !dto.getUserId().equals(userId)) dto.setUserId(userId);
        if (dto.getDate() == null || !dto.getDate().equals(date)) dto.setDate(date);
        
        dailyWorkSummaryIn.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}/date/{date}")
    @Operation(summary = "Deletar resumo diário por usuário e data")
    public ResponseEntity<Void> delete(
            @PathVariable("userId") String userId,
            @PathVariable("date") String date) {
        DailyWorkSummaryDTO existing = dailyWorkSummaryIn.findByUserIdAndDate(userId, date);
        if (existing == null) return ResponseEntity.notFound().build();
        
        dailyWorkSummaryIn.deleteByUserIdAndDate(userId, date);
        return ResponseEntity.noContent().build();
    }
}
