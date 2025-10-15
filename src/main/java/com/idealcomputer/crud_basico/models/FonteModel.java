package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Fonte")
public class FonteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FONTE")
    private Long id;
    @Column(nullable = false, name = "Nome_FONTE")
    private String nome;
    @Column(nullable = false, name = "Marca_FONTE")
    private String marca;
    @Column(nullable = false, name = "Qtd_PotenciaWatts_FONTE")
    private Integer potenciaWatts;
    @Column(nullable = false, name = "Formato_FONTE")
    private String formato;
    @Column(name = "Preco_FONTE")
    private Double preco;

    public FonteModel() {
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
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

    public Integer getPotenciaWatts() {
        return potenciaWatts;
    }

    public void setPotenciaWatts(Integer potenciaWatts) {
        this.potenciaWatts = potenciaWatts;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
