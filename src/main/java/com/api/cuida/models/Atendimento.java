package com.api.cuida.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // muitos atendimentos para um paciente
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    // odontologico, medico etc
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_atendimento")
    private TipoAtendimento tipoAtendimento;

    // comum ou preferencial
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_fila")
    private TipoFila tipoFila;

    @ManyToOne(optional = true) 
    @JoinColumn(name = "id_funcionario", nullable = true)
    @jakarta.annotation.Nullable
    private Funcionario funcionarioResponsavel;

    // data em que foi criado o atendimento, caso seja um atendimento pré-agendado
    // vai ser diferente do dataCheckin
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtendimento;

    // data da confirmação (check-in) do atendimento
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCheckin;

    // data em que foi atendido, ou cancelado o atendimento
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCheckout;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_atendimento")
    private StatusAtendimento statusAtendimento;
}
