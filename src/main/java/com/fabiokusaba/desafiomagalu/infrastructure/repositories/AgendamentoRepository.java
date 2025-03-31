package com.fabiokusaba.desafiomagalu.infrastructure.repositories;

import com.fabiokusaba.desafiomagalu.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
