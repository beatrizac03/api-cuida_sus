package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<Paciente> login(@RequestBody Paciente paciente, HttpSession session) {
        Paciente res = autenticacaoService.login(paciente.getCpf(), paciente.getNomeMae(), paciente.getCidadeNatal());
        
        session.setAttribute("pacienteId", res.getId());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        autenticacaoService.logout(session);
        return ResponseEntity.status(HttpStatus.OK).body("Sessão finalizada com sucesso.");
    }

}
