package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Armazenamento")
public class ArmazenamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Armazenamento")
    private Long id;
    @Column(nullable = false, name = "Nome_ARMAZENAMENTO")
    private String nome;
    @Column(nullable = false, name = "Marca_ARMAZENAMENTO")
    private String marca;
    @Column(nullable = false, name = "Tipo_ARMAZENAMENTO")
    private String tipo;
    @Column(nullable = false, name = "Capacidade_GB_ARMAZENAMENTO")
    private Integer capacidadeGb;
    @Column (name = "Preco_ARMAZENAMENTO")
    private Double preco;

    public ArmazenamentoModel() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidadeGb() {
        return capacidadeGb;
    }

    public void setCapacidadeGb(Integer capacidadeGb) {
        this.capacidadeGb = capacidadeGb;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
