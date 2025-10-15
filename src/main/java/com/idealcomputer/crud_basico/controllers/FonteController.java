package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.FonteModel;
import com.idealcomputer.crud_basico.services.FonteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fontes") // Nova rota base
@CrossOrigin(origins = "http://localhost:5173")
public class FonteController {

    @Autowired
    private FonteService fonteService;

    @GetMapping
    public ResponseEntity<List<FonteModel>> findAll() {
        List<FonteModel> list = fonteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FonteModel> findById(@PathVariable Long id) {
        FonteModel fonte = fonteService.findById(id);
        return ResponseEntity.ok().body(fonte);
    }

    @PostMapping
    public ResponseEntity<FonteModel> create(@RequestBody FonteModel fonte) {
        FonteModel newFonte = fonteService.save(fonte);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newFonte.getId()).toUri();
        return ResponseEntity.created(uri).body(newFonte);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FonteModel> update(@PathVariable Long id, @RequestBody FonteModel fonte) {
        fonte.setId(id);
        FonteModel updatedFonte = fonteService.save(fonte);
        return ResponseEntity.ok().body(updatedFonte);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fonteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}