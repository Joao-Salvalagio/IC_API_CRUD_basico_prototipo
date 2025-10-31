package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Armazenamento")
public class ArmazenamentoModel implements BaseEntity<Long>{
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
    @Column (nullable = false, name = "Preco_ARMAZENAMENTO")
    private Double preco;
}
