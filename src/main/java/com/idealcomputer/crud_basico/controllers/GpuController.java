package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.GpuModel;
import com.idealcomputer.crud_basico.services.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gpus") // Nova rota base para GPUs
@CrossOrigin(origins = "http://localhost:5173")
public class GpuController {

    @Autowired
    private GpuService gpuService;

    // Endpoint para buscar todas as GPUs (GET /api/gpus)
    @GetMapping
    public ResponseEntity<List<GpuModel>> findAll() {
        List<GpuModel> list = gpuService.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar uma GPU por ID (GET /api/gpus/{id})
    @GetMapping(value = "/{id}")
    public ResponseEntity<GpuModel> findById(@PathVariable Long id) {
        GpuModel gpu = gpuService.findById(id);
        return ResponseEntity.ok().body(gpu);
    }

    // Endpoint para criar uma nova GPU (POST /api/gpus)
    @PostMapping
    public ResponseEntity<GpuModel> create(@RequestBody GpuModel gpu) {
        GpuModel newGpu = gpuService.save(gpu);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newGpu.getId()).toUri();
        return ResponseEntity.created(uri).body(newGpu);
    }

    // Endpoint para atualizar uma GPU (PUT /api/gpus/{id})
    @PutMapping(value = "/{id}")
    public ResponseEntity<GpuModel> update(@PathVariable Long id, @RequestBody GpuModel gpu) {
        gpu.setId(id);
        GpuModel updatedGpu = gpuService.save(gpu);
        return ResponseEntity.ok().body(updatedGpu);
    }

    // Endpoint para deletar uma GPU (DELETE /api/gpus/{id})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gpuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}