package com.api.cuida.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.cuida.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByCpfAndNomeMaeAndCidadeNatal(String cpf, String nomeMae, String cidadeNatal);
}
