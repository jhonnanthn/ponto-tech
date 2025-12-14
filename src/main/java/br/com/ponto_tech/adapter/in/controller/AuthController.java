package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.dto.AuthRequestDTO;
import br.com.ponto_tech.application.core.domain.dto.AuthResponseDTO;
import br.com.ponto_tech.application.core.domain.dto.UserDTO;
import br.com.ponto_tech.application.port.in.UserIn;
import br.com.ponto_tech.application.port.in.UserLoginIn;
import br.com.ponto_tech.application.core.domain.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserLoginIn userLoginIn;

    @Autowired
    private UserIn userIn;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final String jwtSecret = "changeit1234567890"; // ideally in application.properties
    private final long jwtExpirationMs = 3600_000; // 1h

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        UserLoginDTO user = userLoginIn.findByUsername(request.getUsername().toLowerCase());
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(401).build();
        }

        UserDTO userDTO = userIn.findByEmail(request.getUsername().toLowerCase());
        if (userDTO == null) {
            return ResponseEntity.status(401).build();
        }

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withClaim("userId", userDTO.getUserId())
                .withClaim("email", userDTO.getEmail())
                .withClaim("fullName", userDTO.getFullName())
                .withClaim("role", userDTO.getRole())
                .withExpiresAt(new Date(Instant.now().toEpochMilli() + jwtExpirationMs))
                .sign(algorithm);

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }
}
