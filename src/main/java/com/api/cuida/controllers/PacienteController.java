package com.api.cuida.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.PacienteService;

@RestController
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    // Recuperar pacientes
    @GetMapping("/pacientes")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    // Recuperar paciente por cpf
    @GetMapping("/pacientes/{cpf}")
    public ResponseEntity<Paciente> listarPacientePorCpf(@PathVariable String cpf) {
        Optional<Paciente> paciente = pacienteService.listarPacientePorCpf(cpf);
        return paciente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Recuperar paciente por id
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> listarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.listarPacientePorId(id);
        return paciente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Inserir paciente no sistema
    @PostMapping("/pacientes")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente) {
        Paciente res = pacienteService.cadastrarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // Atualizar dados de paciente
    @PutMapping("pacientes/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);

            return ResponseEntity.ok(pacienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar paciente do sistema
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
