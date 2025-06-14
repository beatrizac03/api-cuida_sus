package com.api.cuida.DTOs;

import com.api.cuida.models.CargoFuncionario;
import com.api.cuida.models.TipoAtendimento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponseDto {
    private String nome;
    private String cpf;
    private String matricula;
    private String email;
    private CargoFuncionario cargo;
    private TipoAtendimento tipoAtendimento;
}
