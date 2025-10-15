package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.MemoriaRamModel;
import com.idealcomputer.crud_basico.repositories.MemoriaRamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemoriaRamService {

    @Autowired
    private MemoriaRamRepository memoriaRamRepository;

    public MemoriaRamModel findById(Long id) {
        Optional<MemoriaRamModel> memoriaRam = memoriaRamRepository.findById(id);
        return memoriaRam.orElseThrow(() -> new RuntimeException("Memória RAM não encontrada! ID: " + id));
    }

    public List<MemoriaRamModel> findAll() {
        return memoriaRamRepository.findAll();
    }

    @Transactional
    public MemoriaRamModel save(MemoriaRamModel memoriaRam) {
        return memoriaRamRepository.save(memoriaRam);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        memoriaRamRepository.deleteById(id);
    }
}