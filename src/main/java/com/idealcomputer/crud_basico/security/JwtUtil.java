package com.idealcomputer.crud_basico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // 1. CHAVE SECRETA: Esta é a "senha" para criar e validar tokens.
    // !! IMPORTANTE !! Para produção, esta chave DEVE estar no 'application.properties'
    // e ser muito mais longa e complexa. Para desenvolvimento, isto é suficiente.
    private final String SECRET_KEY = "idealcomputer_secret_key_para_gerar_tokens_jwt_2025";

    // 2. TEMPO DE EXPIRAÇÃO: Define por quanto tempo o token é válido (ex: 10 horas)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 horas em milissegundos

    // --- Métodos de Geração de Token ---

    /**
     * Gera um novo token JWT para um usuário.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // (Opcional) Você pode adicionar dados extras ao token aqui (ex: nome, roles)
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // O "assunto" do token (nosso usuário, o e-mail)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Data de criação
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Data de expiração
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Assina com nossa chave secreta
                .compact();
    }

    // --- Métodos de Validação de Token ---

    /**
     * Verifica se um token é válido (compara o usuário e se não expirou).
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

    /**
     * Extrai a data de expiração do token.
     */
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
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}