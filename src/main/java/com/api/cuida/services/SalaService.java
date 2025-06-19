package com.api.cuida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cuida.models.SalaAtendimento;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.repositories.SalaRepository;

@Service
public class SalaService {
    
    @Autowired
    private SalaRepository salaRepository;
    
    // public List<SalaAtendimento> buscarSalasDisponiveis(TipoAtendimento tipoAtendimento, StatusAtendimento statusAtendimento) {
    //     List<SalaAtendimento> salas = salaRepository.findAllByTipoAtendimentoAndStatusAtendimento(tipoAtendimento, statusAtendimento);

    //     return salas;
    // }
}
