package com.fabiokusaba.desafiomagalu.controller;

import com.fabiokusaba.desafiomagalu.business.AgendamentoService;
import com.fabiokusaba.desafiomagalu.controller.dto.in.AgendamentoRequest;
import com.fabiokusaba.desafiomagalu.controller.dto.out.AgendamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> gravarAgendamento(@RequestBody AgendamentoRequest agendamentoRequest) {
        return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamentoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarAgendamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }
}
