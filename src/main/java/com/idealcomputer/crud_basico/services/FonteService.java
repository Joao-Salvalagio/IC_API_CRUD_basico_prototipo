package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.FonteModel;
import com.idealcomputer.crud_basico.repositories.FonteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FonteService extends BaseCrudService<FonteModel, Long, FonteRepository> {

    @Autowired
    public FonteService(FonteRepository repository) {
        super(repository, "Fonte");
    }

    @Override
    @Transactional
    public FonteModel save(FonteModel entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}