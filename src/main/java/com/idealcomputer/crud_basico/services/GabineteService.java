package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.GabineteModel;
import com.idealcomputer.crud_basico.repositories.GabineteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GabineteService extends BaseCrudService<GabineteModel, Long, GabineteRepository> {

    @Autowired
    public GabineteService(GabineteRepository repository) {
        super(repository, "Gabinete");
    }

    @Override
    @Transactional
    public GabineteModel save(GabineteModel entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}