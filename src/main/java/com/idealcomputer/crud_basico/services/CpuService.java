package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.CpuModel;
import com.idealcomputer.crud_basico.repositories.CpuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpuService extends BaseCrudService<CpuModel, Long, CpuRepository> {

    @Autowired
    public CpuService(CpuRepository repository) {
        super(repository, "Processador (CPU)");
    }

    @Override
    @Transactional
    public CpuModel save(CpuModel entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}