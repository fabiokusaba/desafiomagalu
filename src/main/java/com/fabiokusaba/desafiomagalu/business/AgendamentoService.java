package com.fabiokusaba.desafiomagalu.business;

import com.fabiokusaba.desafiomagalu.business.mapper.IAgendamentoMapper;
import com.fabiokusaba.desafiomagalu.controller.dto.in.AgendamentoRequest;
import com.fabiokusaba.desafiomagalu.controller.dto.out.AgendamentoResponse;
import com.fabiokusaba.desafiomagalu.infrastructure.entities.Agendamento;
import com.fabiokusaba.desafiomagalu.infrastructure.exception.NotFoundException;
import com.fabiokusaba.desafiomagalu.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final IAgendamentoMapper agendamentoMapper;

    public AgendamentoResponse gravarAgendamento(AgendamentoRequest agendamentoRequest) {
        return agendamentoMapper.paraResponse(
                agendamentoRepository.save(
                        agendamentoMapper.paraEntity(agendamentoRequest)
                )
        );
    }

    public AgendamentoResponse buscarAgendamentoPorId(Long id) {
        return agendamentoMapper.paraResponse(
                agendamentoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Agendamento com ID: " + id + " não encontrado."))
        );
    }
}
