package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.api.cuida.DTOs.FuncionarioLoginDto;
import com.api.cuida.DTOs.PacienteLoginDto;
import com.api.cuida.DTOs.FuncionarioRegistroDto;
import com.api.cuida.infra.jwt.JWTService;
import com.api.cuida.models.Paciente;
import com.api.cuida.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Autenticação", description = "Lida com a autenticação de usuários, permitindo login e recuperação de informações do usuário autenticado.")
@RestController
public class AuthController {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthService autenticacaoService;

    @Operation(summary = "Gera um token JWT caso o paciente esteja cadastrado.")
    @PostMapping("/auth/login")
    public String login(@RequestBody PacienteLoginDto paciente) {
        Paciente res = autenticacaoService.login(paciente);

        if (res == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Paciente não encontrado");
        }

        return jwtService.generateToken(res.getCpf());
    }

    @Operation(summary = "Retorna o paciente autenticado.")
    @GetMapping("/auth/me")
    public Paciente getMe(@AuthenticationPrincipal Paciente paciente) {
        return paciente;
    }

    @PostMapping("/auth/login/funcionarios")
    public String loginFuncionario(@RequestBody FuncionarioLoginDto funcionario) {
        return jwtService.generateToken(funcionario.getMatricula());
    }

}
