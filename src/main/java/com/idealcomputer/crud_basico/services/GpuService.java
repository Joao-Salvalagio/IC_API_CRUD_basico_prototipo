package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.GpuModel;
import com.idealcomputer.crud_basico.repositories.GpuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpuService extends BaseCrudService<GpuModel, Long, GpuRepository> {

    @Autowired
    public GpuService(GpuRepository repository) {
        super(repository, "GPU");
    }

    @Override
    @Transactional
    public GpuModel save(GpuModel entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}