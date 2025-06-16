package com.api.cuida.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.cuida.DTOs.FuncionarioRegistroDto;
import com.api.cuida.models.Funcionario;
import com.api.cuida.services.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Funcionário", description = "Gerencia funcionários, com operações para listar, cadastrar, atualizar e remover funcionários do sistema.")
@RestController
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    /* TODO: ADICIONAR PAGINATION */
    @Operation(summary = "Lista todos os funcionários cadastrados no sistema.")
    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    // @Operation(summary = "Busca um funcionário pela matrícula.")
    // @GetMapping("/funcionarios/{matricula}")
    // public ResponseEntity<Funcionario> buscarFuncionarioPorMatricula(@PathVariable String matricula) {
    //     Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorMatricula(matricula);
    //     return funcionario.map(ResponseEntity::ok)
    //             .orElseGet(() -> ResponseEntity.notFound().build());
    // }

    // @Operation(summary = "Cadastra um novo funcionário no sistema.")
    // @PostMapping("/funcionarios")
    // public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody FuncionarioRegistroDto funcionario) {
    //     Funcionario res = funcionarioService.cadastrarFuncionario(funcionario);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(res);
    // }

    // @Operation(summary = "Atualiza um funcionário existente no sistema.")
    // @PutMapping("/funcionarios/{matricula}")
    // public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable String matricula,
    //         @RequestBody FuncionarioRegistroDto funcionario) {
    //     try {
    //         Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(matricula, funcionario);

    //         return ResponseEntity.ok(funcionarioAtualizado);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @Operation(summary = "Remove um funcionário do sistema pela matrícula.")
    // @DeleteMapping("/funcionarios/{matricula}")
    // public ResponseEntity<Void> deletarFuncionario(@PathVariable String matricula) {
    //     try {
    //         funcionarioService.deletarFuncionario(matricula);
    //         return ResponseEntity.noContent().build();
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
}
