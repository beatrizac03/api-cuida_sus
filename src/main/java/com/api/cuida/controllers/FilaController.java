package com.api.cuida.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import com.api.cuida.DTOs.FilaRequest;
import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;
import com.api.cuida.services.AtendimentoService;
import com.api.cuida.services.FilaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* FilaController = Para uso no Totem. Cria um atendimento também, mas usa dataCheckin */

@RestController
public class FilaController {
    @Autowired
    private FilaService filaService;

    // @GetMapping("/fila")
    // public ResponseEntity<Integer> listarPosicaoNaFila(HttpServletRequest
    // request, @RequestBody FilaRequest dtoFila) {

    // Paciente paciente = (Paciente) request.getAttribute("paciente");

    // FilaRequest filaRequest = desserializeDto(dtoFila);
    // int posicao = filaService.listarPosicaoNaFila(paciente.getCpf(),
    // filaRequest.getTipoFila(),
    // filaRequest.getTipoAtendimento());

    // return ResponseEntity.ok(posicao);
    // }

    @PostMapping("/fila")
    public ResponseEntity<?> inserirNaFila(HttpServletRequest request, @RequestBody FilaRequest dtoFila) {
        Paciente paciente = (Paciente) request.getAttribute("paciente");

        if (paciente == null) {
            return ResponseEntity.status(403).body("Paciente não autenticado");
        }

        filaService.inserirNaFila(paciente.getCpf(), dtoFila.getTipoFila(), dtoFila.getTipoAtendimento());
        return ResponseEntity.ok("Paciente inserido na fila com sucesso!");
    }

    @DeleteMapping("/fila/{cpf}")
    public String removerDaFila(HttpServletRequest request) {
        // TODO: process DELETE request

        return "Paciente removido da fila com sucesso!";
    }

}
