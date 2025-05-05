package com.api.cuida.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByCpfAndNomeMaeAndCidadeNatal(String cpf, String nomeMae, String cidadeNatal);
}
