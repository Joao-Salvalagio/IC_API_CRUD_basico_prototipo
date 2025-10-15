package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.PlacaMaeModel;
import com.idealcomputer.crud_basico.repositories.PlacaMaeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacaMaeService {

    @Autowired
    private PlacaMaeRepository placaMaeRepository;

    public PlacaMaeModel findById(Long id) {
        Optional<PlacaMaeModel> placaMae = placaMaeRepository.findById(id);
        return placaMae.orElseThrow(() -> new RuntimeException("Placa-mãe não encontrada! ID: " + id));
    }

    public List<PlacaMaeModel> findAll() {
        return placaMaeRepository.findAll();
    }

    @Transactional
    public PlacaMaeModel save(PlacaMaeModel placaMae) {
        return placaMaeRepository.save(placaMae);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        placaMaeRepository.deleteById(id);
    }
}