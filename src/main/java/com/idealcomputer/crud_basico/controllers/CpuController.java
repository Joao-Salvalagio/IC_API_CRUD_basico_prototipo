package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.CpuModel;
import com.idealcomputer.crud_basico.services.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cpus") // Define a rota base para os endpoints de CPU
@CrossOrigin(origins = "http://localhost:5173") // Permite requisições do nosso frontend
public class CpuController {

    @Autowired
    private CpuService cpuService;

    // Endpoint para buscar todas as CPUs (GET /api/cpus)
    @GetMapping
    public ResponseEntity<List<CpuModel>> findAll() {
        List<CpuModel> list = cpuService.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar uma CPU por ID (GET /api/cpus/{id})
    @GetMapping(value = "/{id}")
    public ResponseEntity<CpuModel> findById(@PathVariable Long id) {
        CpuModel cpu = cpuService.findById(id);
        return ResponseEntity.ok().body(cpu);
    }

    // Endpoint para criar uma nova CPU (POST /api/cpus)
    @PostMapping
    public ResponseEntity<CpuModel> create(@RequestBody CpuModel cpu) {
        CpuModel newCpu = cpuService.save(cpu);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newCpu.getId()).toUri();
        return ResponseEntity.created(uri).body(newCpu);
    }

    // Endpoint para atualizar uma CPU (PUT /api/cpus/{id})
    @PutMapping(value = "/{id}")
    public ResponseEntity<CpuModel> update(@PathVariable Long id, @RequestBody CpuModel cpu) {
        cpu.setId(id); // Garante que estamos atualizando a CPU com o ID correto da URL
        CpuModel updatedCpu = cpuService.save(cpu);
        return ResponseEntity.ok().body(updatedCpu);
    }

    // Endpoint para deletar uma CPU (DELETE /api/cpus/{id})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cpuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}