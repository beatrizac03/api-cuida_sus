package com.api.cuida.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PostMapping("/fila/posicao")
    public ResponseEntity<Integer> getPosicao(
            @AuthenticationPrincipal Paciente paciente,
            @RequestParam TipoAtendimento tipoAtendimento) {
        int posicao = filaService.getPosicaoNaFila(paciente.getId(), tipoAtendimento);
        if (posicao == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(posicao);
    }

    @PostMapping("/fila")
    public ResponseEntity<?> inserirNaFila(@AuthenticationPrincipal Paciente paciente,
            @RequestBody FilaRequest dtoFila) {
        Atendimento atendimento = filaService.inserirNaFila(paciente, dtoFila.getTipoFila(),
                dtoFila.getTipoAtendimento());
        return ResponseEntity.ok(atendimento);
    }

    @DeleteMapping("/fila/{cpf}")
    public String removerDaFila(HttpServletRequest request) {
        // TODO: process DELETE request

        return "Paciente removido da fila com sucesso!";
    }

}
