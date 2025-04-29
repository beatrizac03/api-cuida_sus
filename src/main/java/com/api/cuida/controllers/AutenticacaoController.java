package com.api.cuida.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.PacienteService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    // HTTP SESSION: 

    @PostMapping("/login")
    public ResponseEntity<Paciente> logarPaciente(@RequestBody Paciente paciente, HttpSession session) {
        Paciente pacienteAutenticado = autenticacaoService.logarPaciente(paciente.getCpf(), paciente.getNome_mae(), paciente.getCidade_natal(), session);
        return ResponseEntity.ok(pacienteAutenticado);
    }

    @PostMapping("/logout")
    public ResponseEntity<Paciente> deslogarPaciente(HttpSession session) {
        session.invalidate();
    }
    
    
}
