package com.idealcomputer.crud_basico.models;

import com.idealcomputer.crud_basico.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_Usuarios")
public class UserModel implements BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "Nome_USUARIO",  nullable = false)
    private String name;

    @Column(name = "Email_USUARIO",  nullable = false, unique = true)
    private String email;

    @Column(name = "Senha_USUARIO", nullable = false)
    private String password;

    // 2. Renomeamos "function" para "cargo" (o texto livre)
    @Column(name = "Cargo_USUARIO",  nullable = false)
    private String cargo; // Ex: "Cliente", "Desenvolvedor", "CEO"

    @Enumerated(EnumType.STRING)
    @Column(name = "Funcao_USUARIO", nullable = false)
    private UserRole funcao;
}