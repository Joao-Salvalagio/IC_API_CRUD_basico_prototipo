package com.idealcomputer.crud_basico.dto;

import com.idealcomputer.crud_basico.models.*;

public class RecommendationResponseDTO { //Data Transfer Object
    private CpuModel cpu;
    private PlacaMaeModel placaMae;
    private GpuModel gpu;
    private MemoriaRamModel memoriaRam;
    private ArmazenamentoModel armazenamento;
    private FonteModel fonte;
    private GabineteModel gabinete;
    private RefrigeracaoModel refrigeracao;

    public CpuModel getCpu() {
        return cpu;
    }

    public void setCpu(CpuModel cpu) {
        this.cpu = cpu;
    }

    public PlacaMaeModel getPlacaMae() {
        return placaMae;
    }

    public void setPlacaMae(PlacaMaeModel placaMae) {
        this.placaMae = placaMae;
    }

    public GpuModel getGpu() {
        return gpu;
    }

    public void setGpu(GpuModel gpu) {
        this.gpu = gpu;
    }

    public MemoriaRamModel getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(MemoriaRamModel memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public ArmazenamentoModel getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(ArmazenamentoModel armazenamento) {
        this.armazenamento = armazenamento;
    }

    public FonteModel getFonte() {
        return fonte;
    }

    public void setFonte(FonteModel fonte) {
        this.fonte = fonte;
    }

    public GabineteModel getGabinete() {
        return gabinete;
    }

    public void setGabinete(GabineteModel gabinete) {
        this.gabinete = gabinete;
    }

    public RefrigeracaoModel getRefrigeracao() {
        return refrigeracao;
    }

    public void setRefrigeracao(RefrigeracaoModel refrigeracao) {
        this.refrigeracao = refrigeracao;
    }
}
