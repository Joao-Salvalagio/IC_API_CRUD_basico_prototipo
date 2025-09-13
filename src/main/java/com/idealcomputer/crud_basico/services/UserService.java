package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //buscar usuario por ID
    public UserModel findById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! ID: " + id));
    }
    //listar usuarios
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
    //salvar usuarios
    @Transactional //garante que a operacao seja executada em uma
    //transacao segura com o banco
    public UserModel save(UserModel user){
        return userRepository.save(user);
    }
    //deletar usuarios
    @Transactional
    public void deleteById(Long id){
        findById(id); //verifica se o usuario existe
        userRepository.deleteById(id);
    }
}
