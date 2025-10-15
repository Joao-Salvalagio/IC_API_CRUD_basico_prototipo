package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.RefrigeracaoModel;
import com.idealcomputer.crud_basico.repositories.RefrigeracaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefrigeracaoService {

    @Autowired
    private RefrigeracaoRepository refrigeracaoRepository;

    public RefrigeracaoModel findById(Long id) {
        Optional<RefrigeracaoModel> refrigeracao = refrigeracaoRepository.findById(id);
        return refrigeracao.orElseThrow(() -> new RuntimeException("Sistema de refrigeração não encontrado! ID: " + id));
    }

    public List<RefrigeracaoModel> findAll() {
        return refrigeracaoRepository.findAll();
    }

    @Transactional
    public RefrigeracaoModel save(RefrigeracaoModel refrigeracao) {
        return refrigeracaoRepository.save(refrigeracao);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        refrigeracaoRepository.deleteById(id);
    }
}