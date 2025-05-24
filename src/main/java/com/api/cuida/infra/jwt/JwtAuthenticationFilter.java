package com.api.cuida.infra.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.cuida.models.Paciente;
import com.api.cuida.repositories.PacienteRepository;
import com.api.cuida.services.AutenticacaoService;
import com.api.cuida.services.PacienteService;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final PacienteService pacienteService;

    public JwtAuthenticationFilter(JWTService jwtService, PacienteService pacienteService) {
        this.jwtService = jwtService;
        this.pacienteService = pacienteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String cpf = jwtService.extractCpf(token);

            if (cpf != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Paciente userDetails = pacienteService.buscarPorCpf(cpf)
                        .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

                if (jwtService.isTokenValid(token, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
