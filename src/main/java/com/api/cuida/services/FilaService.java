package com.api.cuida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;
import com.api.cuida.repositories.AtendimentoRepository;

@Service
public class FilaService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public int listarPosicaoNaFila(Long id_paciente, TipoFila tipoFila, TipoAtendimento tipoAtendimento) {

        List<Atendimento> fila = atendimentoRepository
                .findByTipoAtendimentoAndTipoFilaAndStatusAtendimentoOrderByDataCheckinAsc(
                        tipoAtendimento,
                        tipoFila,
                        StatusAtendimento.AGUARDANDO_NA_FILA);

        for (int i = 0; i < fila.size(); i++) {
            if (fila.get(i).getPaciente().getId().equals(id_paciente)) {
                return i + 1;
            }
        }

        return -1;
    }

    public Atendimento inserirNaFila(Paciente paciente, TipoFila tipoFila, TipoAtendimento tipoAtendimento) {
        Atendimento atendimento = new Atendimento();

        atendimento.setPaciente(paciente);
        atendimento.setTipoFila(tipoFila);
        atendimento.setTipoAtendimento(tipoAtendimento);
        atendimento.setStatusAtendimento(StatusAtendimento.AGUARDANDO_NA_FILA);
        atendimento.setDataCheckin(java.time.LocalDateTime.now());
        atendimento.setDataAtendimento(null);
        atendimentoRepository.save(atendimento);

        return atendimento;
    }

    // public void removerDaFila(String cpf, TipoFila tipoFila, TipoAtendimento
    // tipoAtendimento) {
    // Paciente paciente = pacienteRepository.findByCpf(cpf)
    // .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    // // Optional<Atendimento> atendimento = atendimentoRepository
    // // .findByPacienteAndTipoFilaAndTipoAtendimento(paciente, tipoFila,
    // // tipoAtendimento);

    // if (atendimento.isPresent()) {
    // atendimentoRepository.delete(atendimento.get());
    // } else {
    // throw new RuntimeException("Paciente não está na fila");
    // }
    // }
}
