package com.api.cuida.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.DTOs.FuncionarioResponseDto;
import com.api.cuida.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByMatricula(String matricula);
    Optional<Funcionario> findByMatriculaAndSenha(String matricula, String senha);
}
