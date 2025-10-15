package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.ArmazenamentoModel;
import com.idealcomputer.crud_basico.services.ArmazenamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/armazenamentos") // Nova rota base
@CrossOrigin(origins = "http://localhost:5173")
public class ArmazenamentoController {

    @Autowired
    private ArmazenamentoService armazenamentoService;

    @GetMapping
    public ResponseEntity<List<ArmazenamentoModel>> findAll() {
        List<ArmazenamentoModel> list = armazenamentoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArmazenamentoModel> findById(@PathVariable Long id) {
        ArmazenamentoModel armazenamento = armazenamentoService.findById(id);
        return ResponseEntity.ok().body(armazenamento);
    }

    @PostMapping
    public ResponseEntity<ArmazenamentoModel> create(@RequestBody ArmazenamentoModel armazenamento) {
        ArmazenamentoModel newArmazenamento = armazenamentoService.save(armazenamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newArmazenamento.getId()).toUri();
        return ResponseEntity.created(uri).body(newArmazenamento);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ArmazenamentoModel> update(@PathVariable Long id, @RequestBody ArmazenamentoModel armazenamento) {
        armazenamento.setId(id);
        ArmazenamentoModel updatedArmazenamento = armazenamentoService.save(armazenamento);
        return ResponseEntity.ok().body(updatedArmazenamento);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        armazenamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}