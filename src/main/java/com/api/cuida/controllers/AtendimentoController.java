package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.services.AtendimentoService;

@RestController
public class AtendimentoController {
    @Autowired
    AtendimentoService atendimentoService;
}
