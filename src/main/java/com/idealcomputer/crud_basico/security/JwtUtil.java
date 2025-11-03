package com.idealcomputer.crud_basico.security;

import com.idealcomputer.crud_basico.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; // Importe o @Value
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey; // Importe a SecretKey
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // 1. Lê os valores do application.properties
    private final SecretKey SECRET_KEY;
    private final long EXPIRATION_TIME;

    // 2. O construtor agora usa @Value para injetar os valores
    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.EXPIRATION_TIME = expiration;
    }

    // --- Métodos de Geração de Token ---

    /**
     * Gera um novo token JWT a partir do UserModel completo.
     */
    public String generateToken(UserModel userModel) {
        Map<String, Object> claims = new HashMap<>();

        // --- MUDANÇA PRINCIPAL: Adicionando os "Claims" ---
        // Adicionamos informações úteis para o frontend dentro do token
        claims.put("nome", userModel.getName());
        claims.put("cargo", userModel.getCargo());
        claims.put("funcao", userModel.getFuncao().name()); // A permissão (ADMINISTRADOR ou USUARIO)

        return createToken(claims, userModel.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // O "assunto" (nosso e-mail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Assina com a nova chave
                .compact();
    }

    // --- Métodos de Validação de Token ---

    /**
     * Verifica se um token é válido.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extrai o nome de usuário (e-mail) de dentro do token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // --- Métodos Auxiliares ---

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método getSigningKey() não é mais necessário, usamos SECRET_KEY diretamente
}