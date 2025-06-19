package com.api.cuida.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.models.SalaAtendimento;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;

public interface SalaRepository extends JpaRepository<SalaAtendimento, Long> {
    Optional<SalaAtendimento> findByNomeSala(String nomeSala);
}
