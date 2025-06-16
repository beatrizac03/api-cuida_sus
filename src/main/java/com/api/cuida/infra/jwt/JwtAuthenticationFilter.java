package com.api.cuida.infra.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.cuida.models.Funcionario;
import com.api.cuida.models.Paciente;
import com.api.cuida.services.FuncionarioService;
import com.api.cuida.services.PacienteService;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Intercepta requisições HTTP para autenticar usuários via JWT.
 * Verifica o cabeçalho Authorization, extrai o token JWT, valida-o e define
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final PacienteService pacienteService;
    private final FuncionarioService funcionarioService;

    public JwtAuthenticationFilter(JWTService jwtService, PacienteService pacienteService,
            FuncionarioService funcionarioService) {
        this.jwtService = jwtService;
        this.pacienteService = pacienteService;
        this.funcionarioService = funcionarioService;
    }

    // @Override
    // protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    //     String path = request.getRequestURI();
    //     return path.startsWith("/auth/") || path.equals("/error");
    // }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            String identificador = jwtService.extractSubject(token);
            String tipoUsuario = jwtService.extractTipo(token);

            if (identificador != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if ("PACIENTE".equalsIgnoreCase(tipoUsuario)) {
                    pacienteService.buscarPorCpf(identificador).ifPresent(paciente -> {
                        if (jwtService.isTokenValid(token, paciente.getUsername())) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    paciente, null, paciente.getAuthorities());
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    });
                } else if ("FUNCIONARIO".equalsIgnoreCase(tipoUsuario)) {
                    funcionarioService.buscarPorMatricula(identificador).ifPresent(funcionario -> {
                        if (jwtService.isTokenValid(token, funcionario.getUsername())) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    funcionario, null, funcionario.getAuthorities());
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    });
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
