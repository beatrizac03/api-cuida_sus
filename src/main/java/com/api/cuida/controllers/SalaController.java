package com.api.cuida.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.api.cuida.models.SalaAtendimento;
import com.api.cuida.models.StatusAtendimento;
import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.services.SalaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SalaController {

    @Autowired
    private SalaService salaService;

    // @GetMapping("/salas/{tipoAtendimento}/{status}")
    // public ResponseEntity<List<SalaAtendimento>> buscarSalasDisponiveis(@PathVariable TipoAtendimento tipoAtendimento, @PathVariable StatusAtendimento statusAtendimento) {
    //     List<SalaAtendimento> salas = salaService.buscarSalasDisponiveis(tipoAtendimento, statusAtendimento);
    //     return ResponseEntity.ok(salas);
    // }
    
}
