package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Memoria_Ram")
public class MemoriaRamModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RAM")
    private Long id;
    @Column(nullable = false, name = "Nome_RAM")
    private String nome;
    @Column(nullable = false, name = "Marca_RAM")
    private String marca;
    @Column(nullable = false, name = "Capacidade_GB_RAM")
    private Integer capacidadeGb;
    @Column(nullable = false, name = "Tipo_DDR_RAM")
    private String tipo;
    @Column(nullable = false, name = "Frequencia_MHZ_RAM")
    private Integer frequenciaMhz;
    @Column(name = "Preco_RAM")
    private Double preco;

    public MemoriaRamModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getCapacidadeGb() {
        return capacidadeGb;
    }

    public void setCapacidadeGb(Integer capacidadeGb) {
        this.capacidadeGb = capacidadeGb;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getFrequenciaMhz() {
        return frequenciaMhz;
    }

    public void setFrequenciaMhz(Integer frequenciaMhz) {
        this.frequenciaMhz = frequenciaMhz;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
