package com.api.cuida.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.DTOs.FilaRequestDto;
import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.services.FilaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/* FilaController = Para uso no Totem. Cria um atendimento também, mas usa dataCheckin */

@Tag(name = "Fila", description = "Gerencia filas de atendimento, com operações para inserir, listar e remover pacientes da fila.")
@RestController
public class FilaController {
    @Autowired
    private FilaService filaService;

    @Operation(summary = "Retorna a posição do paciente na fila para um tipo de atendimento específico.")
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

    @Operation(summary = "Insere um paciente autenticado na fila de atendimento.")
    @PostMapping("/fila")
    public ResponseEntity<?> inserirNaFila(@AuthenticationPrincipal Paciente paciente,
            @RequestBody FilaRequestDto dtoFila) {
        Atendimento atendimento = filaService.inserirNaFila(paciente, dtoFila.getTipoFila(),
                dtoFila.getTipoAtendimento());
        return ResponseEntity.ok(atendimento);
    }

    @Operation(summary = "Retorna a fila (comum e preferencial) de um tipo de atendimento específico.")
    @GetMapping("/fila")
    public List<Atendimento> listarFila(@RequestBody TipoAtendimento tipoAtendimento) {
        return filaService.getFilaIntercalada(tipoAtendimento);
    }

    @Operation(summary = "Paciente autenticado remove-se da fila de atendimento.")
    @DeleteMapping("/fila")
    public ResponseEntity<?> removerDaFila(@AuthenticationPrincipal Paciente paciente) {
        filaService.removerDaFila(paciente);

        return ResponseEntity.noContent().build();
    }

}
