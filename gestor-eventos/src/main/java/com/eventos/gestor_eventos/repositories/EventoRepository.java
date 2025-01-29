package com.eventos.gestor_eventos.repositories;


import com.eventos.gestor_eventos.models.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
