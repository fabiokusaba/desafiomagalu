package com.fabiokusaba.desafiomagalu.business.mapper;

import com.fabiokusaba.desafiomagalu.controller.dto.in.AgendamentoRequest;
import com.fabiokusaba.desafiomagalu.controller.dto.out.AgendamentoResponse;
import com.fabiokusaba.desafiomagalu.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoRequest agendamentoRequest);

    AgendamentoResponse paraResponse(Agendamento agendamento);
}
