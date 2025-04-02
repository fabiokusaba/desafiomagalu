package com.fabiokusaba.desafiomagalu.business;

import com.fabiokusaba.desafiomagalu.business.mapper.IAgendamentoMapper;
import com.fabiokusaba.desafiomagalu.controller.dto.in.AgendamentoRequest;
import com.fabiokusaba.desafiomagalu.controller.dto.out.AgendamentoResponse;
import com.fabiokusaba.desafiomagalu.infrastructure.entities.Agendamento;
import com.fabiokusaba.desafiomagalu.infrastructure.enums.StatusNotificacaoEnum;
import com.fabiokusaba.desafiomagalu.infrastructure.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private IAgendamentoMapper agendamentoMapper;

    private Agendamento agendamentoEntity;
    private AgendamentoRequest agendamentoRequest;
    private AgendamentoResponse agendamentoResponse;

    @BeforeEach
    void setUp() {
        agendamentoEntity = new Agendamento(
                1L,
                "john.doe@email.com",
                "1198765432",
                LocalDateTime.of(2025, 01, 02, 11, 01, 01),
                LocalDateTime.now(),
                null,
                "Seu brinde está disponível para retirada",
                StatusNotificacaoEnum.AGENDADO
        );

        agendamentoRequest = new AgendamentoRequest(
                "john.doe@email.com",
                "1198765432",
                "Seu brinde está disponível para retirada",
                LocalDateTime.of(2025, 01, 02, 11, 01, 01)
        );

        agendamentoResponse = new AgendamentoResponse(
                1L,
                "john.doe@email.com",
                "1198765432",
                "Seu brinde está disponível para retirada",
                LocalDateTime.of(2025, 01, 02, 11, 01, 01),
                StatusNotificacaoEnum.AGENDADO
        );
    }

    @Test
    void deveGravarAgendamentoComSucesso() {
        when(agendamentoMapper.paraEntity(agendamentoRequest)).thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(agendamentoMapper.paraResponse(agendamentoEntity)).thenReturn(agendamentoResponse);

        AgendamentoResponse response = agendamentoService.gravarAgendamento(agendamentoRequest);

        verify(agendamentoMapper, times(1)).paraEntity(agendamentoRequest);
        verify(agendamentoRepository, times(1)).save(agendamentoEntity);
        verify(agendamentoMapper, times(1)).paraResponse(agendamentoEntity);
        assertThat(response).usingRecursiveComparison().isEqualTo(agendamentoResponse);
    }

    @Test
    void deveBuscarAgendamentoPorIdComSucesso() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.ofNullable(agendamentoEntity));
        when(agendamentoMapper.paraResponse(agendamentoEntity)).thenReturn(agendamentoResponse);

        AgendamentoResponse response = agendamentoService.buscarAgendamentoPorId(1L);

        verify(agendamentoRepository, times(1)).findById(1L);
        verify(agendamentoMapper, times(1)).paraResponse(agendamentoEntity);
        assertThat(response).usingRecursiveComparison().isEqualTo(agendamentoResponse);
    }

    @Test
    void deveCancelarAgendamentoComSucesso() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamentoEntity));
        when(agendamentoMapper.paraEntityCancelamento(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity);

        agendamentoService.cancelarAgendamento(1L);

        verify(agendamentoRepository, times(1)).findById(1L);
        verify(agendamentoMapper, times(1)).paraEntityCancelamento(agendamentoEntity);
        verify(agendamentoRepository, times(1)).save(agendamentoEntity);
    }
}
