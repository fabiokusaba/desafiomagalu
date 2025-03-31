package com.fabiokusaba.desafiomagalu.controller.dto.out;

import com.fabiokusaba.desafiomagalu.infrastructure.enums.StatusNoficacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        String emailDestinatario,
        String telefoneDestinatario,
        String mensagem,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataHoraEnvio,
        StatusNoficacaoEnum statusNotificacao) {
}
