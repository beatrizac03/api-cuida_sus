package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.AutenticacaoService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    // HTTP SESSION:

    @PostMapping("/login")
    public ResponseEntity<Paciente> login(@RequestBody Paciente pacienteRequest, HttpSession session) {
        Paciente paciente = autenticacaoService.login(pacienteRequest.getCpf(), pacienteRequest.getNomeMae(),
                pacienteRequest.getCidadeNatal(), session);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        autenticacaoService.logout(session);
        return ResponseEntity.ok().build(); // Responde 200 OK
    }

}
