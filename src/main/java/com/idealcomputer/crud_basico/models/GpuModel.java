package com.idealcomputer.crud_basico.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Gpu")
public class GpuModel implements BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GPU")
    private Long id;
    @Column(nullable = false, name = "Nome_GPU")
    private String nome;
    @Column(nullable = false, name = "Marca_GPU")
    private String marca;
    @Column(nullable = false, name = "MemoriaVRAM_GPU")
    private Integer memoriaVram;
    @Column(nullable = false, name = "Preco_GPU")
    private Double preco;
    @Column(nullable = false, name = "Potencia_Recomendada_W_GPU")
    private Integer potenciaRecomendadaW;
}
