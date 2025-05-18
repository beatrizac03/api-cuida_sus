package com.api.cuida.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.AutenticacaoService;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AutenticacaoService autenticacaoService;

        @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        String cpf = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                cpf = jwtService.extractCpf(jwt);
            } catch (Exception e) {
                // Se o token não for válido, não autentica
            }
        }

        if (cpf != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        Paciente paciente = null;
        try {
            paciente = autenticacaoService.getUserByCpf(cpf);
        } catch (Exception e) {
            // paciente não encontrado
        }

        if (paciente != null && jwtService.isTokenValid(jwt, paciente.getCpf())) {
            // Criar objeto Authentication e setar no contexto
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    paciente, null, List.of() // se tiver roles, coloque aqui
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);

            // opcional: setar paciente no request
            request.setAttribute("paciente", paciente);
        }
    }
        
        filterChain.doFilter(request, response);
    }
}
