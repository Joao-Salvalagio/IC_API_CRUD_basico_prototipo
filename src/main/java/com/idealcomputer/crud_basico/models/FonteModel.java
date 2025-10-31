package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Fonte")
public class FonteModel implements BaseEntity<Long>{
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
    @Column(nullable = false, name = "Preco_FONTE")
    private Double preco;
}
