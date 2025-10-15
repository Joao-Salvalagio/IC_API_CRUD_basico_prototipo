package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.FonteModel;
import com.idealcomputer.crud_basico.repositories.FonteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FonteService {

    @Autowired
    private FonteRepository fonteRepository;

    public FonteModel findById(Long id) {
        Optional<FonteModel> fonte = fonteRepository.findById(id);
        return fonte.orElseThrow(() -> new RuntimeException("Fonte não encontrada! ID: " + id));
    }

    public List<FonteModel> findAll() {
        return fonteRepository.findAll();
    }

    @Transactional
    public FonteModel save(FonteModel fonte) {
        return fonteRepository.save(fonte);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        fonteRepository.deleteById(id);
    }
}