package com.idealcomputer.crud_basico.dto;

import com.idealcomputer.crud_basico.models.*;
import lombok.Data;

@Data
public class RecommendationResponseDTO { //Data Transfer Object
    private CpuModel cpu;
    private PlacaMaeModel placaMae;
    private GpuModel gpu;
    private MemoriaRamModel memoriaRam;
    private ArmazenamentoModel armazenamento;
    private FonteModel fonte;
    private GabineteModel gabinete;
    private RefrigeracaoModel refrigeracao;
}
