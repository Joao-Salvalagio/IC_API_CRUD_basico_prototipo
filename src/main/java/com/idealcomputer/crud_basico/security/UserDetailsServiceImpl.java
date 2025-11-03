package com.idealcomputer.crud_basico.security;

import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.enums.UserRole; // Import corrigido
import com.idealcomputer.crud_basico.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        // CORREÇÃO: Cria a autoridade com o nome EXATO do Enum (ex: "ADMINISTRADOR")
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userModel.getFuncao().name());

        return new User(
                userModel.getEmail(),
                userModel.getPassword(),
                Collections.singletonList(authority)
        );
    }
}