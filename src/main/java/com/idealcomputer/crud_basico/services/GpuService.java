package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.GpuModel;
import com.idealcomputer.crud_basico.repositories.GpuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GpuService {

    @Autowired
    private GpuRepository gpuRepository;

    public GpuModel findById(Long id) {
        Optional<GpuModel> gpu = gpuRepository.findById(id);
        return gpu.orElseThrow(() -> new RuntimeException("GPU não encontrada! ID: " + id));
    }

    public List<GpuModel> findAll() {
        return gpuRepository.findAll();
    }

    @Transactional
    public GpuModel save(GpuModel gpu) {
        return gpuRepository.save(gpu);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        gpuRepository.deleteById(id);
    }
}