package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.dto.RecommendationRequestDTO;
import com.idealcomputer.crud_basico.dto.RecommendationResponseDTO;
import com.idealcomputer.crud_basico.models.*;
import com.idealcomputer.crud_basico.repositories.*;
import lombok.RequiredArgsConstructor; // Importa a anotação
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final CpuRepository cpuRepository;
    private final PlacaMaeRepository placaMaeRepository;
    private final GpuRepository gpuRepository;
    private final MemoriaRamRepository memoriaRamRepository;
    private final ArmazenamentoRepository armazenamentoRepository;
    private final FonteRepository fonteRepository;
    private final GabineteRepository gabineteRepository;
    private final RefrigeracaoRepository refrigeracaoRepository;

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

        double platformBudget = maxBudget * 0.65;

        List<PlatformKit> validKits = allPossibleKits.stream()
                .filter(kit -> kit.totalCost <= platformBudget)
                .filter(kit -> filterKitByUsage(kit, request))
                .sorted(Comparator.comparingDouble((PlatformKit kit) -> kit.totalCost).reversed())
                .collect(Collectors.toList());

        if (validKits.isEmpty()) {
            throw new RuntimeException("Não foi possível encontrar um kit (CPU, Placa-mãe, RAM) para seu orçamento. Tente um valor maior.");
        }

        boolean isBudgetBuild = request.getBudget().equalsIgnoreCase("econômico") ||
                request.getDetail().equalsIgnoreCase("Básico") ||
                request.getDetail().equalsIgnoreCase("Office");

        if (isBudgetBuild) {
            validKits.sort(Comparator.comparingDouble(kit -> kit.totalCost));
        }

        for (PlatformKit currentKit : validKits) {
            double remainingBudget = maxBudget - currentKit.totalCost;

            RefrigeracaoModel selectedRefrigeracao = null;
            if (requiresSeparateCooler(currentKit.cpu)) {
                String coolerType = isHighEndCpu(currentKit.cpu) ? "Water Cooler" : "Air Cooler";
                selectedRefrigeracao = findCheapestCompatibleCooler(refrigeracaoRepository.findAll(), currentKit.cpu.getSoquete(), remainingBudget, coolerType);

                if (selectedRefrigeracao == null && coolerType.equals("Water Cooler")) {
                    selectedRefrigeracao = findCheapestCompatibleCooler(refrigeracaoRepository.findAll(), currentKit.cpu.getSoquete(), remainingBudget, "Air Cooler");
                }

                if (selectedRefrigeracao == null) continue;
                remainingBudget -= selectedRefrigeracao.getPreco();
            }

            GpuModel selectedGpu = null;
            if (!"Estudos".equalsIgnoreCase(request.getUsage()) || "Engenharia".equalsIgnoreCase(request.getDetail())) {
                selectedGpu = findBestGpu(gpuRepository.findAll(), remainingBudget * 0.7);
                if (selectedGpu != null) remainingBudget -= selectedGpu.getPreco();
            }

            double potenciaNecessaria = calculateRequiredWattage(currentKit.cpu, selectedGpu, maxBudget);
            FonteModel selectedFonte = findCheapestFonte(fonteRepository.findAll(), remainingBudget, potenciaNecessaria);
            if (selectedFonte != null) remainingBudget -= selectedFonte.getPreco();

            ArmazenamentoModel selectedArmazenamento = findCheapestArmazenamento(armazenamentoRepository.findAll(), remainingBudget, "SSD NVMe");
            if (selectedArmazenamento == null) {
                selectedArmazenamento = findCheapestArmazenamento(armazenamentoRepository.findAll(), remainingBudget, "SSD SATA");
            }
            if (selectedArmazenamento != null) remainingBudget -= selectedArmazenamento.getPreco();

            GabineteModel selectedGabinete = findCheapestGabinete(gabineteRepository.findAll(), remainingBudget);

            if (selectedArmazenamento != null && selectedFonte != null && selectedGabinete != null && remainingBudget >= 0) {
                RecommendationResponseDTO response = new RecommendationResponseDTO();
                response.setCpu(currentKit.cpu);
                response.setPlacaMae(currentKit.placaMae);
                response.setMemoriaRam(currentKit.memoriaRam);
                response.setGpu(selectedGpu);
                response.setArmazenamento(selectedArmazenamento);
                response.setFonte(selectedFonte);
                response.setGabinete(selectedGabinete);
                response.setRefrigeracao(selectedRefrigeracao);
                return response;
            }
        }

        throw new RuntimeException("Não foi possível montar uma configuração completa dentro do orçamento. Tente um orçamento maior ou aguarde mais peças serem cadastradas.");
    }

    private boolean filterKitByUsage(PlatformKit kit, RecommendationRequestDTO request) {
        String usage = request.getUsage().toLowerCase();
        String detail = request.getDetail().toLowerCase();
        String cpuName = kit.cpu.getNome().toLowerCase();

        if (usage.equals("jogos")) {
            return !cpuName.contains("g");
        }
        if (usage.equals("estudos")) {
            if (detail.equals("engenharia")) { return !cpuName.contains("g"); }
            else { return cpuName.contains("g"); }
        }
        if (usage.equals("trabalho")) {
            if (detail.equals("office")) { return cpuName.contains("g"); }
            else { return !cpuName.contains("g"); }
        }
        return true;
    }

    private boolean requiresSeparateCooler(CpuModel cpu) {
        String name = cpu.getNome().toUpperCase();
        if (name.endsWith("G")) return false;
        if (name.contains("I3-12100F") || name.contains("RYZEN 5 5600")) return false;
        return true;
    }

    private boolean isHighEndCpu(CpuModel cpu) {
        String name = cpu.getNome().toUpperCase();
        return name.contains("RYZEN 7") || name.contains("RYZEN 9") ||
                name.contains("I7") || name.contains("I9") ||
                name.contains("13600K");
    }

    private double calculateRequiredWattage(CpuModel cpu, GpuModel gpu, double budget) {
        double basePower = 150;
        double cpuPower = cpu != null ? (cpu.getPotenciaRecomendadaW() != null ? cpu.getPotenciaRecomendadaW() : 65) : 0; // Fallback de 65W
        double gpuPower = gpu != null ? (gpu.getPotenciaRecomendadaW() != null ? gpu.getPotenciaRecomendadaW() : 0) : 0;

        double totalDemand = basePower + cpuPower + gpuPower;
        double safeWattage = totalDemand * 1.50;

        if (budget > 7000) {
            return Math.max(safeWattage, 750.0);
        }
        return safeWattage;
    }

    private RefrigeracaoModel findCheapestCompatibleCooler(List<RefrigeracaoModel> coolers, String cpuSocket, double budget, String coolerType) {
        return coolers.stream()
                .filter(cooler -> cooler.getTipo().equalsIgnoreCase(coolerType))
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

    private ArmazenamentoModel findCheapestArmazenamento(List<ArmazenamentoModel> items, double budget, String tipo) {
        return items.stream()
                .filter(i -> i.getTipo().equalsIgnoreCase(tipo))
                .filter(i -> i.getPreco() <= budget)
                .min(Comparator.comparing(ArmazenamentoModel::getPreco))
                .orElse(null);
    }

    private FonteModel findCheapestFonte(List<FonteModel> items, double budget, double requiredWattage) {
        return items.stream()
                .filter(f -> f.getPotenciaWatts() >= requiredWattage)
                .filter(f -> f.getPreco() <= budget)
                .min(Comparator.comparing(FonteModel::getPreco))
                .orElse(null);
    }

    private GabineteModel findCheapestGabinete(List<GabineteModel> items, double budget) {
        return items.stream()
                .filter(g -> g.getPreco() <= budget)
                .min(Comparator.comparing(GabineteModel::getPreco))
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
}