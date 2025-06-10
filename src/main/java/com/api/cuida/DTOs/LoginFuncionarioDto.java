package com.api.cuida.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginFuncionarioDto {
    private String matricula;
    private String cpf;
    private String senha;
    private String tipoAtendimento;
}
