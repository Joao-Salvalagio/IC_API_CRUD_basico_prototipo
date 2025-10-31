package com.idealcomputer.crud_basico.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Diz ao Spring para gerenciar este componente
@RequiredArgsConstructor // Cria o construtor para injeção
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Este método é executado para CADA requisição que chega na API.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Pega o cabeçalho "Authorization" da requisição
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 2. Verifica se o cabeçalho existe e se começa com "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Se não tiver token, apenas continua o fluxo
            return; // Sai do filtro
        }

        // 3. Extrai o token (remove o "Bearer ")
        jwt = authHeader.substring(7);

        // 4. Extrai o e-mail (username) de dentro do token
        userEmail = jwtUtil.extractUsername(jwt);

        // 5. Se temos o e-mail e o usuário ainda não está autenticado no contexto de segurança...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Carrega os detalhes do usuário do banco de dados (incluindo as "funcoes")
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 7. Valida o token (compara com os dados do banco e verifica a expiração)
            if (jwtUtil.validateToken(jwt, userDetails)) {

                // 8. Se o token for válido, cria a autenticação
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // Não precisamos de credenciais (senha) aqui, o token já prova quem é
                        userDetails.getAuthorities() // IMPORTANTE: Passa as Funções (Roles) do usuário
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 9. Informa ao Spring Security que este usuário está AUTENTICADO para esta requisição
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 10. Continua o fluxo da requisição
        filterChain.doFilter(request, response);
    }
}