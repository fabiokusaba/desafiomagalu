package com.fabiokusaba.desafiomagalu.controller;

import com.fabiokusaba.desafiomagalu.business.AgendamentoService;
import com.fabiokusaba.desafiomagalu.controller.dto.in.AgendamentoRequest;
import com.fabiokusaba.desafiomagalu.controller.dto.out.AgendamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> gravarAgendamento(@RequestBody AgendamentoRequest agendamentoRequest) {
        return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamentoRequest));
    }
}
