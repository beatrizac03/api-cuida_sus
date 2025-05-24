package com.api.cuida.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    Atendimento findByPaciente(Paciente paciente);

    //
    // List<Atendimento>
    // findByTipoFilaAndTipoAtendimentoAndStatusAtendimentoAndDataCheckinBetween(
    // TipoFila tipoFila,
    // TipoAtendimento tipoAtendimento,
    // StatusAtendimento statusAtendimento);

    List<Atendimento> findByTipoAtendimentoAndTipoFilaAndStatusAtendimentoOrderByDataCheckinAsc(
            TipoAtendimento tipoAtendimento,
            TipoFila tipoFila,
            StatusAtendimento statusAtendimento);

}
