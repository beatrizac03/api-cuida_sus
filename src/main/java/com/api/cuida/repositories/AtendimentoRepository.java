package com.api.cuida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.models.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    
}
