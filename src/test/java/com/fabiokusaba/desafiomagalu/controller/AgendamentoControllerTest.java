package com.fabiokusaba.desafiomagalu.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fabiokusaba.desafiomagalu.business.AgendamentoService;
import com.fabiokusaba.desafiomagalu.controller.dto.in.AgendamentoRequest;
import com.fabiokusaba.desafiomagalu.controller.dto.out.AgendamentoResponse;
import com.fabiokusaba.desafiomagalu.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {

    @InjectMocks
    AgendamentoController agendamentoController;

    @Mock
    AgendamentoService agendamentoService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private AgendamentoRequest agendamentoRequest;
    private AgendamentoResponse agendamentoResponse;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();

        objectMapper.registerModule(new JavaTimeModule());

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
    void deveCriarAgendamentoComSucesso() throws Exception {
        when(agendamentoService.gravarAgendamento(agendamentoRequest)).thenReturn(agendamentoResponse);

        mockMvc.perform(post("/agendamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(agendamentoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agendamentoResponse.id()))
                .andExpect(jsonPath("$.emailDestinatario").value(agendamentoResponse.emailDestinatario()))
                .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoResponse.telefoneDestinatario()))
                .andExpect(jsonPath("$.mensagem").value(agendamentoResponse.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio").value("02-01-2025 11:01:01"))
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

        verify(agendamentoService, times(1)).gravarAgendamento(agendamentoRequest);
    }

    @Test
    void deveBuscarAgendamentoPorIdComSucesso() throws Exception {
        when(agendamentoService.buscarAgendamentoPorId(1L)).thenReturn(agendamentoResponse);

        mockMvc.perform(get("/agendamento/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agendamentoResponse.id()))
                .andExpect(jsonPath("$.emailDestinatario").value(agendamentoResponse.emailDestinatario()))
                .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoResponse.telefoneDestinatario()))
                .andExpect(jsonPath("$.mensagem").value(agendamentoResponse.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio").value("02-01-2025 11:01:01"))
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

        verify(agendamentoService, times(1)).buscarAgendamentoPorId(1L);
    }

    @Test
    void deveCancelarAgendamentoComSucesso() throws Exception {
        doNothing().when(agendamentoService).cancelarAgendamento(1L);

        mockMvc.perform(delete("/agendamento/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(agendamentoService, times(1)).cancelarAgendamento(1L);
    }
}
