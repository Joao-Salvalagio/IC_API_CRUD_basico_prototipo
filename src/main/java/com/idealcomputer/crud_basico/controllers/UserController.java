package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController { // NÃO estende mais BaseCrudController

    // 1. Injetamos o UserService (que agora é independente)
    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    /*
     * 2. Re-implementamos todos os 5 endpoints CRUD
     * que antes eram herdados.
     */

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserModel user) {
        // 3. O método "save" do userService agora irá CRIPTOGRAFAR a senha
        UserModel newUser = userService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserModel user) {
        user.setId(id);
        // 4. O "save" aqui também vai criptografar a senha caso ela seja alterada.
        UserModel updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}