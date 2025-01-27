package com.eventos.gestor_eventos.services;

import com.eventos.gestor_eventos.models.entities.Evento;
import com.eventos.gestor_eventos.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento guardarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public void eliminarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
