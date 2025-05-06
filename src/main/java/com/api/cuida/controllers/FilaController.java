package com.api.cuida.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.models.Fila;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;
import com.api.cuida.services.FilaService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/fila")
public class FilaController {

    @Autowired
    private FilaService filaService;

    @GetMapping
    public ResponseEntity<List<Fila>> exibirFila(@RequestParam TipoAtendimento tipoAtendimento, @RequestParam TipoFila tipoFila) {
        List<Fila> fila = filaService.listarFila(tipoAtendimento, tipoFila);
        return ResponseEntity.ok(fila);
    }
    
    // Ex.: fila?tipoAtendimento=ODONTOLOGIA&tipoFila=PREFERENCIAL
    @PostMapping
    public ResponseEntity<Fila> inserirPacienteNaFila(
        @RequestParam TipoAtendimento tipoAtendimento,
        @RequestParam TipoFila tipoFila,
        HttpSession session) {
        
        Long pacienteId = (Long) session.getAttribute("pacienteId");

        if (pacienteId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Fila novaFila = filaService.inserirPacienteNaFila(pacienteId, tipoAtendimento, tipoFila);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFila);
    }
    
}
