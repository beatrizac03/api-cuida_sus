package com.api.cuida.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String matricula;

    private String nome; 

    @Column(unique = true, nullable = false)
    private String cpf;
    
    private String senha;

    private String email;
    
    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private CargoFuncionario cargo;

    @Enumerated(EnumType.STRING)
    private TipoAtendimento tipoAtendimento;
}
