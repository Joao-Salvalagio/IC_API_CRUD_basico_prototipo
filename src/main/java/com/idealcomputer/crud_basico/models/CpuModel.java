package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Cpu")
public class CpuModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CPU")
    private Long id;
    @Column(nullable = false, name = "Nome_CPU")
    private String nome;
    @Column(nullable = false, name = "Marca_CPU")
    private String marca;
    @Column(nullable = false, name = "Soquete_CPU")
    private String soquete;
    @Column(name = "Preco_CPU")
    private Double preco;
    @Column(nullable = false, name = "Potencia_Recomendada_W_CPU")
    private Integer potenciaRecomendadaW;

    public CpuModel() {
    }

    public Integer getPotenciaRecomendadaW() {
        return potenciaRecomendadaW;
    }

    public void setPotenciaRecomendadaW(Integer potenciaRecomendadaW) {
        this.potenciaRecomendadaW = potenciaRecomendadaW;
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

    public String getSoquete() {
        return soquete;
    }

    public void setSoquete(String soquete) {
        this.soquete = soquete;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
