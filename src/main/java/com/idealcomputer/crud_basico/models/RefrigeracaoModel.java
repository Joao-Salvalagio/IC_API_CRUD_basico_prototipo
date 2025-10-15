package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Refrigeracao")
public class RefrigeracaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REFRIGERACAO")
    private Long id;
    @Column(nullable = false, name = "Nome_REFRIGERACAO")
    private String nome;
    @Column(nullable = false, name = "Marca_REFRIGERACAO")
    private String marca;
    @Column(nullable = false, name = "Tipo_REFRIGERACAO")
    private String tipo;
    @Column(nullable = false, name = "Soquetes_cpu_suportados_REFRIGERACAO")
    private String soquetesCpuSuportados;
    @Column(name = "Preco_REFRIGERACAO")
    private Double preco;

    public RefrigeracaoModel() {
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

    public String getSoquetesCpuSuportados() {
        return soquetesCpuSuportados;
    }

    public void setSoquetesCpuSuportados(String soquetesCpuSuportados) {
        this.soquetesCpuSuportados = soquetesCpuSuportados;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
