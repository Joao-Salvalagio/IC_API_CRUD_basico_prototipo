package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Refrigeracao")
public class RefrigeracaoModel implements BaseEntity<Long>{
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
    @Column(nullable = false, name = "Preco_REFRIGERACAO")
    private Double preco;
}
