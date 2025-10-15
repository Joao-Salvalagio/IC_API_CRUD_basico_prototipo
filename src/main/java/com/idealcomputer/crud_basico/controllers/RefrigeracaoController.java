package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.RefrigeracaoModel;
import com.idealcomputer.crud_basico.services.RefrigeracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/refrigeracoes") // Nova rota base
@CrossOrigin(origins = "http://localhost:5173")
public class RefrigeracaoController {

    @Autowired
    private RefrigeracaoService refrigeracaoService;

    @GetMapping
    public ResponseEntity<List<RefrigeracaoModel>> findAll() {
        List<RefrigeracaoModel> list = refrigeracaoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RefrigeracaoModel> findById(@PathVariable Long id) {
        RefrigeracaoModel refrigeracao = refrigeracaoService.findById(id);
        return ResponseEntity.ok().body(refrigeracao);
    }

    @PostMapping
    public ResponseEntity<RefrigeracaoModel> create(@RequestBody RefrigeracaoModel refrigeracao) {
        RefrigeracaoModel newRefrigeracao = refrigeracaoService.save(refrigeracao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newRefrigeracao.getId()).toUri();
        return ResponseEntity.created(uri).body(newRefrigeracao);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RefrigeracaoModel> update(@PathVariable Long id, @RequestBody RefrigeracaoModel refrigeracao) {
        refrigeracao.setId(id);
        RefrigeracaoModel updatedRefrigeracao = refrigeracaoService.save(refrigeracao);
        return ResponseEntity.ok().body(updatedRefrigeracao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        refrigeracaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}