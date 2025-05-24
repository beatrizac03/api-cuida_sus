package com.api.cuida.DTOs;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.cuida.models.TipoAtendimento;
import com.api.cuida.models.TipoFila;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilaRequest {
    @Autowired
    private TipoFila tipoFila;

    @Autowired
    private TipoAtendimento tipoAtendimento;

}
