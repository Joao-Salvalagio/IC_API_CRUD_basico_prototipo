package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.dto.AuthRequestDTO;
import com.idealcomputer.crud_basico.dto.AuthResponseDTO;
import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.enums.UserRole;
import com.idealcomputer.crud_basico.repositories.UserRepository;
import com.idealcomputer.crud_basico.security.JwtUtil;
import com.idealcomputer.crud_basico.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService; // Removido 'UserDetails'
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequestDTO request) {
        UserModel newUser = new UserModel();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setFuncao(UserRole.USUARIO);
        newUser.setCargo("Cliente");
        userService.save(newUser);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@RequestBody AuthRequestDTO request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Email ou senha inválidos", e);
        }

        // --- MUDANÇA AQUI ---
        // 1. Buscamos o usuário COMPLETO no banco de dados
        UserModel userModel = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("Usuário não encontrado após a autenticação."));

        // 2. Geramos o token usando o UserModel (para incluir os claims)
        final String token = jwtUtil.generateToken(userModel);

        // 3. Retornamos a resposta
        return ResponseEntity.ok(new AuthResponseDTO(token, userModel.getEmail(), userModel.getName()));
    }
}