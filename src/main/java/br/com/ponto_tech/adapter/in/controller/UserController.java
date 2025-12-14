package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.dto.UserDTO;
import br.com.ponto_tech.application.core.domain.dto.CreateUserDTO;
import br.com.ponto_tech.application.port.in.UserIn;
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
@RequestMapping("/v1/users")
@Tag(name = "Users", description = "Endpoints para gerenciar usuários")
public class UserController {

    @Autowired
    private UserIn userIn;

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)))
    })
    public List<UserDTO> findAllUsers() {
        return userIn.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserDTO> findUser(@PathVariable("id") String id) {
        UserDTO users = userIn.findById(id);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Operation(summary = "Criar um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO dto) {
        String userId = userIn.save(dto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId)).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atualização realizada"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UserDTO user) {
        if (user.getUserId() == null || !user.getUserId().equals(id)) {
            user.setUserId(id);
        }
        userIn.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userIn.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
