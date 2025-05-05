package com.api.cuida.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Paciente;
import com.api.cuida.repositories.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> listarPacientePorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public Paciente cadastrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizarPaciente(Long id, Paciente paciente) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado!"); 
        }

        paciente.setId(id);
        
        return pacienteRepository.save(paciente);
    }

    public void deletarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado!");
        }

        pacienteRepository.deleteById(id);
    }
}
