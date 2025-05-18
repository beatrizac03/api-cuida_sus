package com.api.cuida.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // odontologico, medico etc
    @Enumerated(EnumType.STRING)
    private TipoAtendimento tipoAtendimento;

    // comum ou preferencial
    @Enumerated(EnumType.STRING)
    private TipoFila tipoFila;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionarioResponsavel;

    // data da confirmação (check-in) do atendimento
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEntrada;

    // data em que foi atendido, ou cancelado o atendimento
    private LocalDateTime dataSaida;

    private boolean status;
}
