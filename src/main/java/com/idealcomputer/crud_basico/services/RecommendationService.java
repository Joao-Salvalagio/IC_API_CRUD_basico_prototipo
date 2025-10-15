package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.dto.RecommendationRequestDTO;
import com.idealcomputer.crud_basico.dto.RecommendationResponseDTO;
import com.idealcomputer.crud_basico.models.*; // Importa todos os nossos modelos
import com.idealcomputer.crud_basico.repositories.*; // Importa todos os nossos repositórios
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    // 1. Injeta todos os repositórios. Agora o serviço tem acesso a todas as peças.
    @Autowired private CpuRepository cpuRepository;
    @Autowired private PlacaMaeRepository placaMaeRepository;
    @Autowired private GpuRepository gpuRepository;
    @Autowired private MemoriaRamRepository memoriaRamRepository;
    @Autowired private ArmazenamentoRepository armazenamentoRepository;
    @Autowired private FonteRepository fonteRepository;
    @Autowired private GabineteRepository gabineteRepository;
    @Autowired private RefrigeracaoRepository refrigeracaoRepository;

    /**
     * Método principal que gera a recomendação de build.
     * @param request As escolhas do usuário (uso e orçamento).
     * @return Um DTO com a build completa e compatível.
     */
    public RecommendationResponseDTO generateBuild(RecommendationRequestDTO request) {

        // --- LÓGICA DE NEGÓCIO (VERSÃO 1 - MVP) ---
        // Para esta primeira versão, vamos usar uma lógica simplificada para validar o fluxo.
        // A ideia é: pegar a primeira peça de cada categoria que seja compatível com a anterior.

        // Passo 1: Escolher uma CPU (por enquanto, a primeira que encontrarmos no banco)
        List<CpuModel> cpus = cpuRepository.findAll();
        if (cpus.isEmpty()) throw new RuntimeException("Nenhuma CPU cadastrada na base de dados.");
        CpuModel selectedCpu = cpus.get(0);

        // Passo 2: Escolher uma Placa-mãe COMPATÍVEL com a CPU
        List<PlacaMaeModel> placasMae = placaMaeRepository.findAll();
        PlacaMaeModel selectedPlacaMae = placasMae.stream()
                .filter(pm -> pm.getSoqueteCpu().equalsIgnoreCase(selectedCpu.getSoquete()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma placa-mãe compatível encontrada para a CPU: " + selectedCpu.getNome()));

        // Passo 3: Escolher uma Memória RAM COMPATÍVEL com a Placa-mãe
        List<MemoriaRamModel> memoriasRam = memoriaRamRepository.findAll();
        MemoriaRamModel selectedMemoriaRam = memoriasRam.stream()
                .filter(ram -> ram.getTipo().equalsIgnoreCase(selectedPlacaMae.getTipoRamSuportado()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma memória RAM compatível encontrada para a placa-mãe: " + selectedPlacaMae.getNome()));

        // Passo 4: Escolher os outros componentes (por enquanto, apenas o primeiro de cada lista)
        GpuModel selectedGpu = gpuRepository.findAll().stream().findFirst().orElse(null);
        ArmazenamentoModel selectedArmazenamento = armazenamentoRepository.findAll().stream().findFirst().orElse(null);
        FonteModel selectedFonte = fonteRepository.findAll().stream().findFirst().orElse(null);
        GabineteModel selectedGabinete = gabineteRepository.findAll().stream().findFirst().orElse(null);
        RefrigeracaoModel selectedRefrigeracao = refrigeracaoRepository.findAll().stream().findFirst().orElse(null);

        // --- Montagem da Resposta ---
        RecommendationResponseDTO response = new RecommendationResponseDTO();
        response.setCpu(selectedCpu);
        response.setPlacaMae(selectedPlacaMae);
        response.setMemoriaRam(selectedMemoriaRam);
        response.setGpu(selectedGpu);
        response.setArmazenamento(selectedArmazenamento);
        response.setFonte(selectedFonte);
        response.setGabinete(selectedGabinete);
        response.setRefrigeracao(selectedRefrigeracao);

        return response;
    }
}