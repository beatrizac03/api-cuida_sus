package com.api.cuida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.repositories.AtendimentoRepository;
import com.api.cuida.repositories.PacienteRepository;

@Service
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public Atendimento buscarAtendimentoAtualDoPaciente(Paciente paciente) {
        return atendimentoRepository
                .findFirstByPacienteAndStatusAtendimentoOrderByDataCheckinDesc(
                        paciente, StatusAtendimento.AGUARDANDO_NA_FILA)
                .orElseThrow(() -> new RuntimeException("Nenhum atendimento em andamento"));
    }

}
