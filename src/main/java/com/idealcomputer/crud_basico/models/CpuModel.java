package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Cpu")
public class CpuModel implements BaseEntity<Long>{
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
    @Column(nullable = false, name = "Preco_CPU")
    private Double preco;
    @Column(nullable = false, name = "Potencia_Recomendada_W_CPU")
    private Integer potenciaRecomendadaW;
}
