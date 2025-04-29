package com.api.cuida.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Funcionario {

    @Id
    private Long id;

    private String nome;

    private String cpf;

    private String tipo_especialidade;
}
