package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.models.BaseEntity;
import com.idealcomputer.crud_basico.services.BaseCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/*
 * Esta é a nossa classe base de Controller.
 * <T> - O Model (ex: CpuModel), que AGORA garantimos que implementa BaseEntity
 * <ID> - O tipo do ID (ex: Long)
 * <S> - O Service (ex: CpuService)
 */
public abstract class BaseCrudController<
        T extends BaseEntity<ID>,
        ID,
        S extends BaseCrudService<T, ID, ?>> {

    protected final S service;

    public BaseCrudController(S service) {
        this.service = service;
    }

    // --- ENDPOINTS GENÉRICOS (JÁ HERDADOS) ---

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T newEntity = service.save(entity);
        // Agora podemos chamar getId() com segurança!
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(newEntity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        // Agora podemos chamar setId() com segurança!
        entity.setId(id);
        T updatedEntity = service.save(entity);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}