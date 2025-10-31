package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.RefrigeracaoModel;
import com.idealcomputer.crud_basico.repositories.RefrigeracaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefrigeracaoService extends BaseCrudService<RefrigeracaoModel, Long, RefrigeracaoRepository> {

    @Autowired
    public RefrigeracaoService(RefrigeracaoRepository repository) {
        super(repository, "Refrigeração");
    }

    @Override
    @Transactional
    public RefrigeracaoModel save(RefrigeracaoModel entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}