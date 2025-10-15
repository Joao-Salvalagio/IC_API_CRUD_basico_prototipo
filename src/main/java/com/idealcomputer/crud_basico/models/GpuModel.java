package com.idealcomputer.crud_basico.models;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_Gpu")
public class GpuModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GPU")
    private Long id;
    @Column(nullable = false, name = "Nome_GPU")
    private String nome;
    @Column(nullable = false, name = "Marca_GPU")
    private String marca;
    @Column(name = "MemoriaVRAM_GPU")
    private Integer memoriaVram;
    @Column(name = "Preco_GPU")
    private Double preco;

    public GpuModel() {
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

    public Integer getMemoriaVram() {
        return memoriaVram;
    }

    public void setMemoriaVram(Integer memoriaVram) {
        this.memoriaVram = memoriaVram;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
