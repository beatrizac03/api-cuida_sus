package com.api.cuida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.DTOs.PacienteLoginDto;
import com.api.cuida.models.Paciente;
import com.api.cuida.repositories.PacienteRepository;

@Service
public class AuthService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente login(PacienteLoginDto paciente) {
        return pacienteRepository
                .findByCpfAndNomeMaeAndCidadeNatal(paciente.getCpf(), paciente.getNomeMae(), paciente.getCidadeNatal())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Paciente getUserByCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));
    }
}
