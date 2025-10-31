package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.PlacaMaeModel;
import com.idealcomputer.crud_basico.repositories.PlacaMaeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacaMaeService extends BaseCrudService<PlacaMaeModel, Long, PlacaMaeRepository> {

    @Autowired
    public PlacaMaeService(PlacaMaeRepository repository) {
        super(repository, "Placa-mãe");
    }

    @Override
    @Transactional
    public PlacaMaeModel save(PlacaMaeModel entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}