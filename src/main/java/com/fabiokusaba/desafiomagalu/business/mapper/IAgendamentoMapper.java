package com.fabiokusaba.desafiomagalu.business.mapper;

import com.fabiokusaba.desafiomagalu.controller.dto.in.AgendamentoRequest;
import com.fabiokusaba.desafiomagalu.controller.dto.out.AgendamentoResponse;
import com.fabiokusaba.desafiomagalu.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoRequest agendamentoRequest);

    AgendamentoResponse paraResponse(Agendamento agendamento);

    @Mapping(target = "dataHoraModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(StatusNotificacaoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);
}
