package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.dto.AuthRequestDTO;
import com.idealcomputer.crud_basico.dto.AuthResponseDTO;
import com.idealcomputer.crud_basico.models.UserModel;
import com.idealcomputer.crud_basico.enums.UserRole; // Verifique se o import está correto
import com.idealcomputer.crud_basico.repositories.UserRepository;
import com.idealcomputer.crud_basico.security.JwtUtil;
import com.idealcomputer.crud_basico.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    /**
     * Endpoint para registrar um novo usuário.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequestDTO request) {
        UserModel newUser = new UserModel();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());

        // --- LÓGICA DE DEFAULT CORRIGIDA (Sua Regra) ---
        // 1. Define a Função (Role) padrão como USUARIO.
        newUser.setFuncao(UserRole.USUARIO);

        // 2. Define o Cargo (Title) padrão como Cliente, sem ler da requisição.
        newUser.setCargo("Cliente");
        // --- FIM DA LÓGICA ---

        userService.save(newUser);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    /**
     * Endpoint para autenticar um usuário e retornar um token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@RequestBody AuthRequestDTO request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Email ou senha inválidos", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        UserModel userModel = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("Erro ao buscar dados do usuário após login."));

        return ResponseEntity.ok(new AuthResponseDTO(token, userModel.getEmail(), userModel.getName()));
    }
}