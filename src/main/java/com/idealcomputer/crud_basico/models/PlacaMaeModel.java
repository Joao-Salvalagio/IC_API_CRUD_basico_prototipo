package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Placa_Mae")
public class PlacaMaeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLACAMAE")
    private Long id;
    @Column(nullable = false, name = "Nome_PLACAMAE")
    private String nome;
    @Column(nullable = false, name = "Marca_PLACAMAE")
    private String marca;
    @Column(nullable = false, name = "Soquete_CPU_PLACAMAE")
    private String soqueteCpu;
    @Column(nullable = false, name = "Tipo_RAM_Suportado_PLACAMAE")
    private String tipoRamSuportado;
    @Column(nullable = false, name = "Formato_PLACAMAE")
    private String formato;
    @Column(name = "Preco_PLACAMAE")
    private Double preco;

    public PlacaMaeModel() {
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

    public String getSoqueteCpu() {
        return soqueteCpu;
    }

    public void setSoqueteCpu(String soqueteCpu) {
        this.soqueteCpu = soqueteCpu;
    }

    public String getTipoRamSuportado() {
        return tipoRamSuportado;
    }

    public void setTipoRamSuportado(String tipoRamSuportado) {
        this.tipoRamSuportado = tipoRamSuportado;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
