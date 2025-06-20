package com.api.cuida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.cuida.DTOs.AtualizarAtendimentoDto;
import com.api.cuida.DTOs.ChamarPacienteDto;
import com.api.cuida.models.Atendimento;
import com.api.cuida.models.Funcionario;
import com.api.cuida.models.Paciente;
import com.api.cuida.models.SalaAtendimento;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.repositories.AtendimentoRepository;
import com.api.cuida.repositories.PacienteRepository;
import com.api.cuida.repositories.SalaRepository;

@Service
public class AtendimentoService {
        @Autowired
        private AtendimentoRepository atendimentoRepository;
        @Autowired
        private PacienteRepository pacienteRepository;

        @Autowired
        private SalaRepository salaRepository;

        public Atendimento buscarAtendimentoAtualDoPaciente(Paciente paciente) {
                return atendimentoRepository
                                .findFirstByPacienteAndStatusAtendimentoOrderByDataCheckinDesc(
                                                paciente, StatusAtendimento.AGUARDANDO_NA_FILA)
                                .orElseThrow(
                                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                "Nenhum atendimento em andamento"));
        }

        public void chamarPacienteNaSala(ChamarPacienteDto dto, Funcionario funcionarioLogado) {
                Atendimento atendimento = atendimentoRepository.findById(dto.getIdAtendimento())
                                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));

                SalaAtendimento sala = salaRepository.findById(dto.getIdSala())
                                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

                atendimento.setSalaAtendimento(sala);
                atendimento.setFuncionarioResponsavel(funcionarioLogado);
                atendimento.setStatusAtendimento(StatusAtendimento.EM_ANDAMENTO);

                atendimentoRepository.save(atendimento);
        }

        public List<Atendimento> listarAtendimentos(Funcionario funcionarioLogado) {
                List<Atendimento> atendimentos = atendimentoRepository
                                .findAllByFuncionarioResponsavel_Id(funcionarioLogado.getId());

                return atendimentos;
        }

        public void atualizarAtendimento(Funcionario funcionarioLogado, AtualizarAtendimentoDto dto) {
                Atendimento atendimento = atendimentoRepository.findById(dto.getIdAtendimento())
                                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));

                if (dto.getDataCheckout() != null) {
                        atendimento.setDataCheckout(dto.getDataCheckout());
                }
                atendimento.setStatusAtendimento(dto.getStatusAtendimento());

                atendimentoRepository.save(atendimento);
        }

}
