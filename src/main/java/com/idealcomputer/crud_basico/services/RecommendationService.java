package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.dto.RecommendationRequestDTO;
import com.idealcomputer.crud_basico.dto.RecommendationResponseDTO;
import com.idealcomputer.crud_basico.models.*;
import com.idealcomputer.crud_basico.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final CpuRepository cpuRepository;
    private final PlacaMaeRepository placaMaeRepository;
    private final GpuRepository gpuRepository;
    private final MemoriaRamRepository memoriaRamRepository;
    private final ArmazenamentoRepository armazenamentoRepository;
    private final FonteRepository fonteRepository;
    private final GabineteRepository gabineteRepository;
    private final RefrigeracaoRepository refrigeracaoRepository;

    @Autowired
    public RecommendationService(CpuRepository cpuRepository, PlacaMaeRepository placaMaeRepository, GpuRepository gpuRepository, MemoriaRamRepository memoriaRamRepository, ArmazenamentoRepository armazenamentoRepository, FonteRepository fonteRepository, GabineteRepository gabineteRepository, RefrigeracaoRepository refrigeracaoRepository) {
        this.cpuRepository = cpuRepository;
        this.placaMaeRepository = placaMaeRepository;
        this.gpuRepository = gpuRepository;
        this.memoriaRamRepository = memoriaRamRepository;
        this.armazenamentoRepository = armazenamentoRepository;
        this.fonteRepository = fonteRepository;
        this.gabineteRepository = gabineteRepository;
        this.refrigeracaoRepository = refrigeracaoRepository;
    }

    // Classe auxiliar interna para representar um "Kit de Plataforma"
    private static class PlatformKit {
        CpuModel cpu;
        PlacaMaeModel placaMae;
        MemoriaRamModel memoriaRam;
        double totalCost;

        PlatformKit(CpuModel cpu, PlacaMaeModel placaMae, MemoriaRamModel memoriaRam) {
            this.cpu = cpu;
            this.placaMae = placaMae;
            this.memoriaRam = memoriaRam;
            this.totalCost = cpu.getPreco() + placaMae.getPreco() + memoriaRam.getPreco();
        }
    }

    public RecommendationResponseDTO generateBuild(RecommendationRequestDTO request) {

        double maxBudget = getBudgetLimit(request.getBudget());

        List<PlatformKit> allPossibleKits = new ArrayList<>();
        for (CpuModel cpu : cpuRepository.findAll()) {
            for (PlacaMaeModel pm : placaMaeRepository.findAll()) {
                if (pm.getSoqueteCpu().equalsIgnoreCase(cpu.getSoquete())) {
                    for (MemoriaRamModel ram : memoriaRamRepository.findAll()) {
                        if (ram.getTipo().equalsIgnoreCase(pm.getTipoRamSuportado())) {
                            allPossibleKits.add(new PlatformKit(cpu, pm, ram));
                        }
                    }
                }
            }
        }

        double platformBudget = maxBudget * 0.65; // Aumentamos um pouco a margem para o kit

        List<PlatformKit> validKits = allPossibleKits.stream()
                .filter(kit -> kit.totalCost <= platformBudget)
                .filter(kit -> {
                    String usage = request.getUsage().toLowerCase();
                    String cpuName = kit.cpu.getNome().toLowerCase();
                    if (usage.equals("jogos")) {
                        return !cpuName.contains("g");
                    }
                    if (usage.equals("estudos")) {
                        if ("Engenharia".equalsIgnoreCase(request.getDetail())) {
                            return !cpuName.contains("g");
                        } else {
                            return cpuName.contains("g");
                        }
                    }
                    return true;
                })
                // Ordena os kits do melhor (mais caro) para o pior (mais barato)
                .sorted(Comparator.comparingDouble(kit -> -kit.totalCost))
                .collect(Collectors.toList());

        if (validKits.isEmpty()) {
            throw new RuntimeException("Não foi possível encontrar um kit (CPU, Placa-mãe, RAM) para seu orçamento. Tente um valor maior.");
        }

        // --- NOVA LÓGICA DE TENTATIVA E FALLBACK ---
        for (PlatformKit currentKit : validKits) {
            double remainingBudget = maxBudget - currentKit.totalCost;

            RefrigeracaoModel selectedRefrigeracao = null;
            if (requiresSeparateCooler(currentKit.cpu)) {
                selectedRefrigeracao = findCheapestCompatibleCooler(refrigeracaoRepository.findAll(), currentKit.cpu.getSoquete(), remainingBudget);
                if (selectedRefrigeracao == null) continue; // Se não couber o cooler, pula para o próximo kit
                remainingBudget -= selectedRefrigeracao.getPreco();
            }

            GpuModel selectedGpu = null;
            String usage = request.getUsage().toLowerCase();
            if (usage.equals("jogos") || usage.equals("trabalho") || (usage.equals("estudos") && "Engenharia".equalsIgnoreCase(request.getDetail()))) {
                selectedGpu = findBestGpu(gpuRepository.findAll(), remainingBudget * 0.7);
                if (selectedGpu != null) remainingBudget -= selectedGpu.getPreco();
            }

            ArmazenamentoModel selectedArmazenamento = findCheapestArmazenamento(armazenamentoRepository.findAll(), remainingBudget);
            if (selectedArmazenamento != null) remainingBudget -= selectedArmazenamento.getPreco();

            FonteModel selectedFonte = findCheapestFonte(fonteRepository.findAll(), remainingBudget);
            if (selectedFonte != null) remainingBudget -= selectedFonte.getPreco();

            GabineteModel selectedGabinete = findCheapestGabinete(gabineteRepository.findAll(), remainingBudget);
            if (selectedGabinete != null) remainingBudget -= selectedGabinete.getPreco();

            // Se conseguimos selecionar todas as peças essenciais e o orçamento não estourou
            if (selectedArmazenamento != null && selectedFonte != null && selectedGabinete != null && remainingBudget >= 0) {
                // SUCESSO! Encontramos uma build viável.
                RecommendationResponseDTO response = new RecommendationResponseDTO();
                response.setCpu(currentKit.cpu);
                response.setPlacaMae(currentKit.placaMae);
                response.setMemoriaRam(currentKit.memoriaRam);
                response.setGpu(selectedGpu);
                response.setArmazenamento(selectedArmazenamento);
                response.setFonte(selectedFonte);
                response.setGabinete(selectedGabinete);
                response.setRefrigeracao(selectedRefrigeracao);
                return response; // Retorna a primeira build que deu certo
            }
            // Se falhou, o loop continua para o próximo kit (fallback)
        }

        // Se o loop terminar e nenhuma build foi encontrada
        throw new RuntimeException("Não foi possível montar uma configuração completa dentro do orçamento. Tente um orçamento maior ou aguarde mais peças serem cadastradas.");
    }

    private boolean requiresSeparateCooler(CpuModel cpu) {
        String name = cpu.getNome().toUpperCase();
        return name.contains("RYZEN 7") || name.contains("RYZEN 9") ||
                name.contains("I7") || name.contains("I9") ||
                name.endsWith("X") || name.endsWith("K") || name.endsWith("KF");
    }

    private RefrigeracaoModel findCheapestCompatibleCooler(List<RefrigeracaoModel> coolers, String cpuSocket, double budget) {
        return coolers.stream()
                .filter(cooler -> cooler.getSoquetesCpuSuportados().toUpperCase().contains(cpuSocket.toUpperCase()))
                .filter(cooler -> cooler.getPreco() <= budget)
                .min(Comparator.comparing(RefrigeracaoModel::getPreco))
                .orElse(null);
    }

    private GpuModel findBestGpu(List<GpuModel> gpus, double budget) {
        return gpus.stream()
                .filter(g -> g.getPreco() <= budget)
                .max(Comparator.comparing(GpuModel::getPreco))
                .orElse(null);
    }

    private double getBudgetLimit(String budgetCategory) {
        return switch (budgetCategory.toLowerCase()) {
            case "econômico" -> 4000.00;
            case "intermediário" -> 7000.00;
            case "alto" -> 12000.00;
            case "extremo" -> 25000.00;
            default -> 7000.00;
        };
    }

    private ArmazenamentoModel findCheapestArmazenamento(List<ArmazenamentoModel> items, double budget) {
        return items.stream().filter(i -> i.getPreco() <= budget).min(Comparator.comparing(ArmazenamentoModel::getPreco)).orElse(null);
    }

    private FonteModel findCheapestFonte(List<FonteModel> items, double budget) {
        return items.stream().filter(i -> i.getPreco() <= budget).min(Comparator.comparing(FonteModel::getPreco)).orElse(null);
    }

    private GabineteModel findCheapestGabinete(List<GabineteModel> items, double budget) {
        return items.stream().filter(i -> i.getPreco() <= budget).min(Comparator.comparing(GabineteModel::getPreco)).orElse(null);
    }
}