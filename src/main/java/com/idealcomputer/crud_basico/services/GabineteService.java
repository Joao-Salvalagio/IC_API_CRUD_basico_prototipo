package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.GabineteModel;
import com.idealcomputer.crud_basico.repositories.GabineteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GabineteService {

    @Autowired
    private GabineteRepository gabineteRepository;

    public GabineteModel findById(Long id) {
        Optional<GabineteModel> gabinete = gabineteRepository.findById(id);
        return gabinete.orElseThrow(() -> new RuntimeException("Gabinete não encontrado! ID: " + id));
    }

    public List<GabineteModel> findAll() {
        return gabineteRepository.findAll();
    }

    @Transactional
    public GabineteModel save(GabineteModel gabinete) {
        return gabineteRepository.save(gabinete);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        gabineteRepository.deleteById(id);
    }
}