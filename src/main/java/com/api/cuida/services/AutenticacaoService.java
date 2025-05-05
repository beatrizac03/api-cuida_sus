package com.api.cuida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.Paciente;
import com.api.cuida.repositories.PacienteRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AutenticacaoService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente login(String cpf, String nomeMae, String cidadeNatal) {
        return pacienteRepository.findByCpfAndNomeMaeAndCidadeNatal(cpf, nomeMae, cidadeNatal)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public void logout(HttpSession session) {
        session.invalidate(); 
    }
}
