package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Gabinete")
public class GabineteModel implements BaseEntity<Long>{
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
    @Column(nullable = false, name = "Preco_GABINETE")
    private Double preco;
}
