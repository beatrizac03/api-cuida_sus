package com.api.cuida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.repositories.AtendimentoRepository;
import com.api.cuida.repositories.PacienteRepository;

@Service
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void inserirNaFila(String cpf, String tipoAtendimento, String tipoFila) {   
        
    }

}
