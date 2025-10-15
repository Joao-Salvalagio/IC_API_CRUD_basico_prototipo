package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.GabineteModel;
import com.idealcomputer.crud_basico.services.GabineteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gabinetes") // Nova rota base
@CrossOrigin(origins = "http://localhost:5173")
public class GabineteController {

    @Autowired
    private GabineteService gabineteService;

    @GetMapping
    public ResponseEntity<List<GabineteModel>> findAll() {
        List<GabineteModel> list = gabineteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GabineteModel> findById(@PathVariable Long id) {
        GabineteModel gabinete = gabineteService.findById(id);
        return ResponseEntity.ok().body(gabinete);
    }

    @PostMapping
    public ResponseEntity<GabineteModel> create(@RequestBody GabineteModel gabinete) {
        GabineteModel newGabinete = gabineteService.save(gabinete);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newGabinete.getId()).toUri();
        return ResponseEntity.created(uri).body(newGabinete);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GabineteModel> update(@PathVariable Long id, @RequestBody GabineteModel gabinete) {
        gabinete.setId(id);
        GabineteModel updatedGabinete = gabineteService.save(gabinete);
        return ResponseEntity.ok().body(updatedGabinete);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gabineteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}