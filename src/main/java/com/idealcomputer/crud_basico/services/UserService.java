package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Importe o PasswordEncoder
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService { // NÃO estende mais BaseCrudService

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 1. Injeta o PasswordEncoder

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 2. Re-implementamos os métodos que estavam na classe base
    public UserModel findById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! ID: " + id));
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id); // Garante que o usuário existe
        userRepository.deleteById(id);
    }

    // 3. Implementamos o método SAVE com a lógica de criptografia
    @Transactional
    public UserModel save(UserModel user) {
        /*
         * Lógica de Criptografia:
         * Nós pegamos a senha que veio do frontend (em texto puro),
         * criptografamos ela usando o BCrypt (o "encoder"),
         * e salvamos o resultado (o "hash") no banco de dados.
         * Nós NUNCA salvamos a senha em texto puro.
         */
        String senhaCriptografada = passwordEncoder.encode(user.getPassword());
        user.setPassword(senhaCriptografada);

        return userRepository.save(user);
    }
}