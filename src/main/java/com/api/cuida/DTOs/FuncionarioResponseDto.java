package com.api.cuida.DTOs;

import com.api.cuida.models.CargoFuncionario;
import com.api.cuida.models.Funcionario;
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
    private String cargo;
    private TipoAtendimento tipoAtendimento;

    // public FuncionarioResponseDto(Funcionario f) {
    //     this.nome = f.getNome();
    //     this.cpf = f.getCpf();
    //     this.matricula = f.getMatricula();
    //     this.email = f.getEmail();
    //     this.cargo = f.getCargo();
    //     this.tipoAtendimento = f.getTipoAtendimento();
    // }
}
