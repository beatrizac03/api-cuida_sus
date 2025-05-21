package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.AtendimentoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FilaController {
    @Autowired
    AtendimentoService atendimentoService;

    @PostMapping("/fila")
    public ResponseEntity<Paciente> inserirNaFila(HttpServletRequest request,
            @RequestParam String tipoAtendimento,
            @RequestParam String tipoFila) {
        Paciente paciente = (Paciente) request.getAttribute("paciente");

        atendimentoService.inserirNaFila(paciente.getCpf(), tipoAtendimento, tipoFila);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/fila")
    public String fila(@RequestParam String param) {
        return new String();
    }

    @DeleteMapping("/fila/{cpf}")
    public String removerDaFila(@PathVariable String cpf) {
        // TODO: process DELETE request

        return "Atendimento removido da fila com sucesso!";
    }

}
