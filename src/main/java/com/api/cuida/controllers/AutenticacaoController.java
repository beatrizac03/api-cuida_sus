package com.api.cuida.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.api.cuida.infra.security.JWTService;
import com.api.cuida.models.Paciente;
import com.api.cuida.services.AutenticacaoService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoController {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/auth/login")
    public String login(@RequestBody Paciente paciente) {
        Paciente res = autenticacaoService.login(paciente.getCpf(), paciente.getNomeMae(), paciente.getCidadeNatal());
        
        return jwtService.generateToken(res.getCpf());
    }

    @PostMapping("/auth/logout")
    public String logout() {
        return "Logout realizado com sucesso";
    }

}
