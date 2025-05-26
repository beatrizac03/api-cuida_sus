package com.api.cuida.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.cuida.DTOs.PacienteDto;
import com.api.cuida.models.Paciente;
import com.api.cuida.services.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Paciente", description = "Gerencia pacientes, com operações para listar, cadastrar, atualizar e remover pacientes do sistema.")
@RestController
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Lista todos os pacientes cadastrados no sistema.")
    @GetMapping("/pacientes")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Cadastra um novo paciente no sistema.")
    @PostMapping("/pacientes")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody PacienteDto paciente) {
        Paciente res = pacienteService.cadastrarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @Operation(summary = "Lista um paciente pelo ID do banco de dados.")
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> listarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.listarPacientePorId(id);
        return paciente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista um paciente pelo CPF.")
    @GetMapping("/pacientes/{cpf}")
    public ResponseEntity<Optional<Paciente>> listarPacientePorCpf(@PathVariable String cpf) {
        Optional<Paciente> paciente = pacienteService.buscarPorCpf(cpf);

        return ResponseEntity.ok(paciente);
    }

    @Operation(summary = "Atualiza os dados de um paciente pelo ID.")
    @PutMapping("pacientes/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);

            return ResponseEntity.ok(pacienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove um paciente do sistema pelo ID do banco de dados.")
    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        try {
            pacienteService.deletarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
