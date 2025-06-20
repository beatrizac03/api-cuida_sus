package com.api.cuida.DTOs;

import java.time.LocalDateTime;

import com.api.cuida.models.StatusAtendimento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarAtendimentoDto {
    private Long idAtendimento;
    private StatusAtendimento statusAtendimento;
    private LocalDateTime dataCheckout;
}
