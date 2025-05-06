package com.api.cuida.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.models.Fila;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;

public interface FilaRepository extends JpaRepository<Fila, Long> {
    List<Fila> findByTipoAtendimentoAndTipoFilaAndAtendidoFalseOrderByDataEntradaAsc(
        TipoAtendimento tipoAtendimento, TipoFila tipoFila);
}
