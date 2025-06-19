package com.api.cuida.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.services.AtendimentoService;
import com.api.cuida.services.FilaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/* AtendimentoController = Para gerenciar atendimentos pré-agendados */

@RestController
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping("/atendimentos/me")
    public ResponseEntity<Atendimento> getAtendimentoAtual(@AuthenticationPrincipal Paciente paciente) {
        Atendimento atendimento = atendimentoService.buscarAtendimentoAtualDoPaciente(paciente);
        return ResponseEntity.ok(atendimento);
    }

}
