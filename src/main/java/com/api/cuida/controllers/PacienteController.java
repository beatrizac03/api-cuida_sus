package com.api.cuida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.models.Paciente;
import com.api.cuida.services.PacienteService;

@RestController
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    // TODO: recuperar pacientes
    public ResponseEntity<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    // TODO: recuperar paciente por cpf
    

    // TODO: inserir paciente no sistema

    // TODO: atualizar dados de paciente
}
