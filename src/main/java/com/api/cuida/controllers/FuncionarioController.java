package com.api.cuida.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.cuida.models.Funcionario;
import com.api.cuida.services.FuncionarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Funcionário", description = "Gerencia funcionários, com operações para listar, cadastrar, atualizar e remover funcionários do sistema.")
@RestController
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping("/funcionarios")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario res = funcionarioService.cadastrarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> listarFuncionarioPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.listarFuncionarioPorId(id);
        return funcionario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
            @RequestBody Funcionario funcionario) {
        try {
            Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionario);

            return ResponseEntity.ok(funcionarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
