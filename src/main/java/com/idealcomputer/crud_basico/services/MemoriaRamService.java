package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.MemoriaRamModel;
import com.idealcomputer.crud_basico.repositories.MemoriaRamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoriaRamService extends BaseCrudService<MemoriaRamModel, Long, MemoriaRamRepository> {

    @Autowired
    public MemoriaRamService(MemoriaRamRepository repository) {
        super(repository, "Memória RAM");
    }

    @Override
    @Transactional
    public MemoriaRamModel save(MemoriaRamModel entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}