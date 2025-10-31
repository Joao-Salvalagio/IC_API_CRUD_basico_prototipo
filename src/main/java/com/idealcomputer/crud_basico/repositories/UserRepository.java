package com.idealcomputer.crud_basico.repositories;

import com.idealcomputer.crud_basico.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // Importe o Optional

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    // --- NOVO MÉTODO ADICIONADO ---
    // Apenas por declarar este método, o Spring Data JPA automaticamente
    // cria a query "SELECT * FROM tb_usuarios WHERE email_usuario = ?"
    Optional<UserModel> findByEmail(String email);

}