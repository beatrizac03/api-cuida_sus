package com.api.cuida.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Paciente;
import com.api.cuida.repositories.PacienteRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AutenticacaoService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Optional<Paciente> autenticar(String cpf, String nomeMae, String cidadeNatal, HttpSession session) {
        Paciente paciente = pacienteRepository.buscarDadosUsuario(cpf, nomeMae, cidadeNatal);
        session.setAttribute("pacienteId", paciente.getId());
        return paciente;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
