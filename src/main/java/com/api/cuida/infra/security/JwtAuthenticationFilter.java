package com.api.cuida.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.AutenticacaoService;
import com.api.cuida.services.PacienteService;

import io.jsonwebtoken.io.IOException;
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
            cpf = jwtService.extractCpf(jwt);
        }

        if (cpf != null) {
            Paciente paciente = autenticacaoService.getUserByCpf(cpf);
            
            if (jwtService.isTokenValid(jwt, paciente.getCpf())) {
                // Autenticação válida - você pode armazenar o user no request se precisar
                request.setAttribute("user", paciente);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
