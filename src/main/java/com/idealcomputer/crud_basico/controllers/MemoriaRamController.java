package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.MemoriaRamModel;
import com.idealcomputer.crud_basico.services.MemoriaRamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/memorias-ram") // Nova rota base para Memórias RAM
@CrossOrigin(origins = "http://localhost:5173")
public class MemoriaRamController {

    @Autowired
    private MemoriaRamService memoriaRamService;

    // Endpoint para buscar todas as Memórias RAM (GET /api/memorias-ram)
    @GetMapping
    public ResponseEntity<List<MemoriaRamModel>> findAll() {
        List<MemoriaRamModel> list = memoriaRamService.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar uma Memória RAM por ID (GET /api/memorias-ram/{id})
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemoriaRamModel> findById(@PathVariable Long id) {
        MemoriaRamModel memoriaRam = memoriaRamService.findById(id);
        return ResponseEntity.ok().body(memoriaRam);
    }

    // Endpoint para criar uma nova Memória RAM (POST /api/memorias-ram)
    @PostMapping
    public ResponseEntity<MemoriaRamModel> create(@RequestBody MemoriaRamModel memoriaRam) {
        MemoriaRamModel newMemoriaRam = memoriaRamService.save(memoriaRam);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newMemoriaRam.getId()).toUri();
        return ResponseEntity.created(uri).body(newMemoriaRam);
    }

    // Endpoint para atualizar uma Memória RAM (PUT /api/memorias-ram/{id})
    @PutMapping(value = "/{id}")
    public ResponseEntity<MemoriaRamModel> update(@PathVariable Long id, @RequestBody MemoriaRamModel memoriaRam) {
        memoriaRam.setId(id);
        MemoriaRamModel updatedMemoriaRam = memoriaRamService.save(memoriaRam);
        return ResponseEntity.ok().body(updatedMemoriaRam);
    }

    // Endpoint para deletar uma Memória RAM (DELETE /api/memorias-ram/{id})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memoriaRamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}