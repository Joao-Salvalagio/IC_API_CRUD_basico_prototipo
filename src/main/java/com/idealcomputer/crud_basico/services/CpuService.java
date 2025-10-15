package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.CpuModel;
import com.idealcomputer.crud_basico.repositories.CpuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CpuService {

    @Autowired
    private CpuRepository cpuRepository;

    public CpuModel findById(Long id) {
        Optional<CpuModel> cpu = cpuRepository.findById(id);
        return cpu.orElseThrow(() -> new RuntimeException("CPU não encontrada! ID: " + id));
    }

    public List<CpuModel> findAll() {
        return cpuRepository.findAll();
    }

    @Transactional
    public CpuModel save(CpuModel cpu) {
        return cpuRepository.save(cpu);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        cpuRepository.deleteById(id);
    }
}