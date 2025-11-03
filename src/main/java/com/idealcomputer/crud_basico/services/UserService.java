package com.idealcomputer.crud_basico.services;

import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends BaseCrudService<UserModel, Long, UserRepository> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        super(repository, "Usuário");
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserModel save(UserModel entity) {
        // Se a entidade já tem um ID, é uma atualização (UPDATE)
        if (entity.getId() != null) {
            // Busca o usuário original no banco
            UserModel userOriginal = findById(entity.getId());

            // Se a senha enviada pelo formulário estiver vazia ou nula...
            if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
                // ...mantém a senha antiga que já estava no banco.
                entity.setPassword(userOriginal.getPassword());
            } else {
                // ...senão, criptografa a nova senha.
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            }
        } else {
            // Se for uma entidade nova (CREATE), criptografa a senha enviada.
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }

        // Salva a entidade (nova ou atualizada)
        return super.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}