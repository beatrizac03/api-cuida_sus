package com.api.cuida.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.DTOs.AtualizarAtendimentoDto;
import com.api.cuida.DTOs.ChamarPacienteDto;
import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Funcionario;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.services.AtendimentoService;
import com.api.cuida.services.FilaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;



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

    // id do atendimento
    @PutMapping("/atendimentos/{id}")
    public ResponseEntity<Void> chamarPacienteNaSala(@AuthenticationPrincipal Funcionario funcionario,
            @RequestBody ChamarPacienteDto dto) {
        atendimentoService.chamarPacienteNaSala(dto, funcionario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atendimentos/att")
    public ResponseEntity<Void> atualizarAtendimento(@AuthenticationPrincipal Funcionario funcionario, @RequestBody AtualizarAtendimentoDto dto) {
        atendimentoService.atualizarAtendimento(funcionario, dto);
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/atendimentos")
    public ResponseEntity<List<Atendimento>> listarAtendimentos(@AuthenticationPrincipal Funcionario funcionario) {
        List<Atendimento> atendimentos = atendimentoService.listarAtendimentos(funcionario);

        return ResponseEntity.ok(atendimentos);
    }
    

}
