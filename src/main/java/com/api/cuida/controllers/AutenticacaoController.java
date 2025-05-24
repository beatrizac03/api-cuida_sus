package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.api.cuida.infra.jwt.JWTService;
import com.api.cuida.models.Paciente;
import com.api.cuida.services.AutenticacaoService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AutenticacaoController {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/auth/login")
    public String login(@RequestBody Paciente paciente) {
        Paciente res = autenticacaoService.login(paciente.getCpf(), paciente.getNomeMae(), paciente.getCidadeNatal());

        if (res == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Paciente não encontrado");
        }

        return jwtService.generateToken(res.getCpf());
    }

    @PostMapping("/auth/logout")
    public String logout() {
        return "Logout realizado com sucesso";
    }

    @GetMapping("/auth/me")
    public Paciente getMe(@AuthenticationPrincipal Paciente paciente) {
        return paciente;
    }

}
