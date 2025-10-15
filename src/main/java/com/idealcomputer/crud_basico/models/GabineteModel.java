package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Gabinete")
public class GabineteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GABINETE")
    private Long id;
    @Column(nullable = false, name = "Nome_GABINETE")
    private String nome;
    @Column(nullable = false, name = "Marca_GABINETE")
    private String marca;
    @Column(nullable = false, name = "Formatos_de_placa_mae_suportados_GABINETE")
    private String formatosPlacaMaeSuportados;
    @Column(name = "Preco_GABINETE")
    private Double preco;

    public GabineteModel() {
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

    public String getFormatosPlacaMaeSuportados() {
        return formatosPlacaMaeSuportados;
    }

    public void setFormatosPlacaMaeSuportados(String formatosPlacaMaeSuportados) {
        this.formatosPlacaMaeSuportados = formatosPlacaMaeSuportados;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
