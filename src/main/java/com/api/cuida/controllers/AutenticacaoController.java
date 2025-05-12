package com.api.cuida.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.AutenticacaoService;

import org.springframework.web.bind.annotation.*;

@RestController
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Paciente paciente) {
        Paciente res = autenticacaoService.login(paciente.getCpf(), paciente.getNomeMae(), paciente.getCidadeNatal());
        
        if (res != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("ok", true);
            response.put("message", "Autenticado com sucesso");
            return ResponseEntity.ok(response);
        } 
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("ok", false);
        errorResponse.put("message", "Paciente não encontrado");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.status(HttpStatus.OK).body("Sessão finalizada com sucesso.");
    }

}
