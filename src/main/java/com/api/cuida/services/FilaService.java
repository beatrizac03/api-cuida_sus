package com.api.cuida.services;

import java.io.ObjectInputFilter.Status;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;
import com.api.cuida.repositories.AtendimentoRepository;
import com.api.cuida.repositories.PacienteRepository;

@Service
public class FilaService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // public int listarPosicaoNaFila(String cpf, TipoFila tipoFila, TipoAtendimento tipoAtendimento) {
    //     Paciente paciente = pacienteRepository.findByCpf(cpf)
    //             .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    //     Atendimento atendimento = atendimentoRepository.findByPaciente(paciente);

    //     List<Atendimento> fila = atendimentoRepository
    //             .findByTipoFilaAndTipoAtendimentoAndStatusAtendimentoAndDataCheckinBetween(tipoFila, tipoAtendimento,
    //                     atendimento.getStatusAtendimento());

    //     for (int i = 0; i < fila.size(); i++) {
    //         if (fila.get(i).getPaciente().getId().equals(paciente.getId())) {
    //             // posição começa em 1
    //             return i + 1;
    //         }
    //     }

    //     throw new RuntimeException("Paciente não está na fila");
    // }

    public void inserirNaFila(String cpf, TipoFila tipoFila, TipoAtendimento tipoAtendimento) {
        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Atendimento atendimento = new Atendimento();

        atendimento.setPaciente(paciente);
        atendimento.setTipoFila(tipoFila);
        atendimento.setTipoAtendimento(tipoAtendimento);
        atendimento.setStatusAtendimento(StatusAtendimento.AGUARDANDO_NA_FILA);
        atendimento.setDataCheckin(java.time.LocalDateTime.now());

        atendimentoRepository.save(atendimento);
    }

    // public void removerDaFila(String cpf, TipoFila tipoFila, TipoAtendimento tipoAtendimento) {
    //     Paciente paciente = pacienteRepository.findByCpf(cpf)
    //             .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    //     // Optional<Atendimento> atendimento = atendimentoRepository
    //     // .findByPacienteAndTipoFilaAndTipoAtendimento(paciente, tipoFila,
    //     // tipoAtendimento);

    //     if (atendimento.isPresent()) {
    //         atendimentoRepository.delete(atendimento.get());
    //     } else {
    //         throw new RuntimeException("Paciente não está na fila");
    //     }
    // }
}
