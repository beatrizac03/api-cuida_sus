package com.api.cuida.services;

import java.util.ArrayList;
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

    public List<Atendimento> getFilaIntercalada(TipoAtendimento tipoAtendimento) {
        List<Atendimento> preferencial = atendimentoRepository
                .findByTipoAtendimentoAndTipoFilaAndStatusAtendimentoOrderByDataCheckinAsc(
                        tipoAtendimento, TipoFila.PREFERENCIAL, StatusAtendimento.AGUARDANDO_NA_FILA);

        List<Atendimento> comum = atendimentoRepository
                .findByTipoAtendimentoAndTipoFilaAndStatusAtendimentoOrderByDataCheckinAsc(
                        tipoAtendimento, TipoFila.COMUM, StatusAtendimento.AGUARDANDO_NA_FILA);

        List<Atendimento> filaFinal = new ArrayList<>();
        int i = 0, j = 0;
        int ratio = 2; // 2 preferenciais para 1 comum

        while (i < preferencial.size() || j < comum.size()) {
            for (int k = 0; k < ratio && i < preferencial.size(); k++) {
                filaFinal.add(preferencial.get(i++));
            }
            if (j < comum.size()) {
                filaFinal.add(comum.get(j++));
            }
        }

        return filaFinal;
    }

    public int getPosicaoNaFila(Long idPaciente, TipoAtendimento tipoAtendimento) {
        List<Atendimento> fila = getFilaIntercalada(tipoAtendimento);
        for (int i = 0; i < fila.size(); i++) {
            if (fila.get(i).getPaciente().getId().equals(idPaciente)) {
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
