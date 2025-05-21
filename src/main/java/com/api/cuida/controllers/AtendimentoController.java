package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.services.AtendimentoService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AtendimentoController {
    @Autowired
    AtendimentoService atendimentoService;

    @PostMapping("/atendimento")
    public String criarAtendimento(HttpServletRequest request) {
        //TODO: process POST request
        return "Atendimento criado com sucesso!";
    }
    
}
