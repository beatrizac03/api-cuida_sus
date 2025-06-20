package com.api.cuida.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
        Atendimento findByPaciente(Paciente paciente);

        List<Atendimento> findByTipoAtendimentoAndTipoFilaAndStatusAtendimentoOrderByDataCheckinAsc(
                        TipoAtendimento tipoAtendimento, TipoFila tipoFila, StatusAtendimento statusAtendimento);

        List<Atendimento> findByPacienteAndStatusAtendimento(Paciente paciente, StatusAtendimento statusAtendimento);

        Optional<Atendimento> findFirstByPacienteAndStatusAtendimentoOrderByDataCheckinDesc(
                        Paciente paciente,
                        StatusAtendimento status);

        List<Atendimento> findByTipoAtendimentoAndTipoFilaAndStatusAtendimentoInOrderByDataCheckinAsc(
                        TipoAtendimento tipoAtendimento,
                        TipoFila tipoFila,
                        List<StatusAtendimento> statusList);

        List<Atendimento> findAllByFuncionarioResponsavel_Id(Long funcionarioId);

}
