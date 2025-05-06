package com.api.cuida.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Fila;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;
import com.api.cuida.repositories.FilaRepository;
import com.api.cuida.repositories.PacienteRepository;

@Service
public class FilaService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private FilaRepository filaRepository;

    public Fila inserirPacienteNaFila(Long pacienteId, TipoAtendimento tipoAtendimento, TipoFila tipoFila) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Fila fila = new Fila();
        fila.setPaciente(paciente);
        fila.setTipoAtendimento(tipoAtendimento);
        fila.setTipoFila(tipoFila);
        fila.setDataEntrada(LocalDateTime.now());
        fila.setAtendido(false);

        return filaRepository.save(fila);
    }

    public List<Fila> listarFila(TipoAtendimento tipoAtendimento, TipoFila tipoFila) {
        return filaRepository.findByTipoAtendimentoAndTipoFilaAndAtendidoFalseOrderByDataEntradaAsc(tipoAtendimento, tipoFila);
    }
}
