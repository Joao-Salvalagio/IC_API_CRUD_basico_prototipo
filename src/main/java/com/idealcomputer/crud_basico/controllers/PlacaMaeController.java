package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.PlacaMaeModel;
import com.idealcomputer.crud_basico.services.PlacaMaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/placas-mae") // Nova rota base para placas-mãe
@CrossOrigin(origins = "http://localhost:5173") // Mantém a permissão para o frontend
public class PlacaMaeController {

    @Autowired
    private PlacaMaeService placaMaeService;

    // Endpoint para buscar todas as placas-mãe (GET /api/placas-mae)
    @GetMapping
    public ResponseEntity<List<PlacaMaeModel>> findAll() {
        List<PlacaMaeModel> list = placaMaeService.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar uma placa-mãe por ID (GET /api/placas-mae/{id})
    @GetMapping(value = "/{id}")
    public ResponseEntity<PlacaMaeModel> findById(@PathVariable Long id) {
        PlacaMaeModel placaMae = placaMaeService.findById(id);
        return ResponseEntity.ok().body(placaMae);
    }

    // Endpoint para criar uma nova placa-mãe (POST /api/placas-mae)
    @PostMapping
    public ResponseEntity<PlacaMaeModel> create(@RequestBody PlacaMaeModel placaMae) {
        PlacaMaeModel newPlacaMae = placaMaeService.save(placaMae);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPlacaMae.getId()).toUri();
        return ResponseEntity.created(uri).body(newPlacaMae);
    }

    // Endpoint para atualizar uma placa-mãe (PUT /api/placas-mae/{id})
    @PutMapping(value = "/{id}")
    public ResponseEntity<PlacaMaeModel> update(@PathVariable Long id, @RequestBody PlacaMaeModel placaMae) {
        placaMae.setId(id);
        PlacaMaeModel updatedPlacaMae = placaMaeService.save(placaMae);
        return ResponseEntity.ok().body(updatedPlacaMae);
    }

    // Endpoint para deletar uma placa-mãe (DELETE /api/placas-mae/{id})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        placaMaeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}