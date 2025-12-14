package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.dto.CreateUserLoginDTO;
import br.com.ponto_tech.application.core.domain.dto.UserLoginDTO;
import br.com.ponto_tech.application.port.in.UserLoginIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/user-logins")
@Tag(name = "UserLogins", description = "Endpoints para gerenciar logins de usuários")
public class UserLoginController {

    @Autowired
    private UserLoginIn userLoginIn;

    @PostMapping
    @Operation(summary = "Criar um novo login de usuário")
    public ResponseEntity<Void> create(@RequestBody CreateUserLoginDTO dto) {
        String username = userLoginIn.save(dto);
        return ResponseEntity.created(URI.create("/v1/user-logins/" + username)).build();
    }

    @GetMapping("/{username}")
    @Operation(summary = "Buscar login por username")
    public ResponseEntity<UserLoginDTO> findByUsername(@PathVariable String username) {
        UserLoginDTO dto = userLoginIn.findByUsername(username.toLowerCase());
        if (dto == null) return ResponseEntity.notFound().build();
        // Do not expose passwordHash
        dto.setPasswordHash(null);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Deletar login por username")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        userLoginIn.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }
}

