package com.fabiokusaba.desafiomagalu.infrastructure.entities;

import com.fabiokusaba.desafiomagalu.infrastructure.enums.StatusNotificacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailDestinatario;
    private String telefoneDestinatario;
    private LocalDateTime dataHoraEnvio;
    private LocalDateTime dataHoraAgendamento;
    private LocalDateTime dataHoraModificacao;
    private String mensagem;
    private StatusNotificacaoEnum statusNotificacao;

    @PrePersist
    private void prePersist() {
        dataHoraAgendamento = LocalDateTime.now();
        statusNotificacao = StatusNotificacaoEnum.AGENDADO;
    }
}
