package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Placa_Mae")
public class PlacaMaeModel implements BaseEntity<Long>{
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
    @Column(nullable = false, name = "Preco_PLACAMAE")
    private Double preco;
}
