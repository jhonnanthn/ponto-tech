package br.com.ponto_tech.adapter.in.controller;

import br.com.ponto_tech.application.core.domain.entity.Users;
import br.com.ponto_tech.application.port.in.UserIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserIn userIn;

    @GetMapping
    public List<Users> findAllUsers() {
        return userIn.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findUser(@PathVariable("id") String id) {
        Users users = userIn.findById(id);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody Users users) {
        userIn.save(users);
        // Location header with created resource URI
        return ResponseEntity.created(URI.create("/users/" + users.getUserId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody Users users) {
        // ensure path id matches payload id
        if (users.getUserId() == null || !users.getUserId().equals(id)) {
            users.setUserId(id);
        }
        userIn.update(users);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userIn.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
