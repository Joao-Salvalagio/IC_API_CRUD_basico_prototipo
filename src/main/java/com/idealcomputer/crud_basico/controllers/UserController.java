package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")//conexao com o frontend
@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    //endpoint para buscar todos os usuarios (read)
    //mapeado para requisicao do GET /usuarios
    @GetMapping
    public ResponseEntity<List<UserModel>> findAll(){
        List<UserModel> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //endpoint para buscar usuario por ID (busca especifica/read)
    //mapeado para requisicao do GET /usuarios{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable Long id){
        UserModel user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    //endpoint para criar um novo usuario (create)
    //mapeado para requisicao POST /usuarios
    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserModel user){
        UserModel newUser = userService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    //endpoint para atualizar um usuario existente (update)
    //mapeado para requisicao PUT /usuarios{id}
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserModel user){
        user.setId(id); //garante que estamos atualizando o usuario com o ID correto
        UserModel updatedUser = userService.save(user);
        return ResponseEntity.ok().body(updatedUser);
    }

    //endpoint para deletar um usuario (delete)
    //mapeado para a requisicao DELETE /usuarios{id}
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}