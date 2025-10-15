package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.ArmazenamentoModel;
import com.idealcomputer.crud_basico.repositories.ArmazenamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmazenamentoService {

    @Autowired
    private ArmazenamentoRepository armazenamentoRepository;

    public ArmazenamentoModel findById(Long id) {
        Optional<ArmazenamentoModel> armazenamento = armazenamentoRepository.findById(id);
        return armazenamento.orElseThrow(() -> new RuntimeException("Armazenamento não encontrado! ID: " + id));
    }

    public List<ArmazenamentoModel> findAll() {
        return armazenamentoRepository.findAll();
    }

    @Transactional
    public ArmazenamentoModel save(ArmazenamentoModel armazenamento) {
        return armazenamentoRepository.save(armazenamento);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        armazenamentoRepository.deleteById(id);
    }
}