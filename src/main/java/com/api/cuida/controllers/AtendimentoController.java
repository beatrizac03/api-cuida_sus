package com.api.cuida.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.models.Atendimento;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.services.AtendimentoService;
import com.api.cuida.services.FilaService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/* AtendimentoController = Para gerenciar atendimentos pré-agendados */

@RestController
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private FilaService filaService;

    @PostMapping("/atendimento")
    public String criarAtendimento(HttpServletRequest request) {
        //TODO: process POST request
        return "Atendimento criado com sucesso!";
    }
    
}
