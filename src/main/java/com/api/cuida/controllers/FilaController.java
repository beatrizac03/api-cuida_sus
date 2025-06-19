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
import com.api.cuida.models.Funcionario;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.StatusAtendimento;
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
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping("/fila")
    public ResponseEntity<?> inserirNaFila(@AuthenticationPrincipal Paciente paciente,
            @RequestBody FilaRequestDto dtoFila) {
        try {
            Atendimento atendimento = filaService.inserirNaFila(paciente, dtoFila.getTipoFila(),
                    dtoFila.getTipoAtendimento());
            return ResponseEntity.ok(atendimento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna a fila (comum + preferencial) de um tipo de atendimento específico.")
    @GetMapping("/fila/{tipoAtendimento}")
    public List<Atendimento> listarFila(@PathVariable TipoAtendimento tipoAtendimento) {
        return filaService.getFilaIntercalada(tipoAtendimento);
    }

    // Um funcionário pode remover um paciente da fila, mas ele também pode se
    // remover
    @Operation(summary = "Paciente autenticado remove-se da fila de atendimento.")
    @DeleteMapping("/fila")
    public ResponseEntity<?> removerDaFila(@AuthenticationPrincipal Paciente paciente) {
        filaService.removerDaFila(paciente);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Funcionário chama paciente, vinculando statusAtendimento e nome da sala")
    @PutMapping("/fila/{idAtendimento}")
    public String chamarPacienteNaSala(@AuthenticationPrincipal Funcionario funcionario,
            @PathVariable Long idAtendimento, StatusAtendimento statusAtendimento, String nomeSala) {

        // consultar id sala de atendimento especifico

        filaService.chamarPacienteNaSala(idAtendimento, statusAtendimento, nomeSala);
        return "Atendimento alterado";
    }

}
