package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.entity.Users;
import br.com.ponto_tech.application.port.in.UserIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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
                            schema = @Schema(implementation = Users.class)))
    })
    public List<Users> findAllUsers() {
        return userIn.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Users> findUser(@PathVariable("id") String id) {
        Users users = userIn.findById(id);
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
    public ResponseEntity<Void> createUser(@RequestBody Users users) {
        userIn.save(users);
        return ResponseEntity.created(URI.create("/users/" + users.getUserId())).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atualização realizada"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody Users users) {
        if (users.getUserId() == null || !users.getUserId().equals(id)) {
            users.setUserId(id);
        }
        userIn.update(users);
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
