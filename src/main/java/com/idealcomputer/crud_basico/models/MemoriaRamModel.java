package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Memoria_Ram")
public class MemoriaRamModel implements BaseEntity<Long>{
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
    @Column(nullable = false, name = "Preco_RAM")
    private Double preco;
}
