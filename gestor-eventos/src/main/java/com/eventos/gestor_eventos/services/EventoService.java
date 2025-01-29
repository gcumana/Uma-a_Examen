package com.eventos.gestor_eventos.services;

import com.eventos.gestor_eventos.clients.AsistenteClientRest;
import com.eventos.gestor_eventos.models.entities.Evento;
import com.eventos.gestor_eventos.models.entities.EventoAsistentes;
import com.eventos.gestor_eventos.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eventos.gestor_eventos.models.Asistente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AsistenteClientRest asistenteClientRest;

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento guardarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void eliminarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    public Optional<Evento> actualizarEvento(Long id, Evento evento) {
        return eventoRepository.findById(id).map(eventoExistente -> {
            evento.setId(id);
            return eventoRepository.save(evento);
        });
    }

    public List<Asistente> listarAsistentesDeEvento(Long eventoId) {
        Optional<Evento> evento = eventoRepository.findById(eventoId);
        if (evento.isPresent()) {
            List<Asistente> asistentes = new ArrayList<>();
            // Llenar la lista de asistentes usando el asistenteId de cada EventoAsistentes
            for (EventoAsistentes eventoAsistente : evento.get().getEventoAsistentes()) {
                Asistente asistente = asistenteClientRest.findById(eventoAsistente.getAsistenteId());
                asistentes.add(asistente);
            }
            return asistentes;
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

    public List<Evento> listarEventosDeAsistente(Long asistenteId) {
        List<Evento> eventos = asistenteClientRest.findEventosByAsistenteId(asistenteId);
        if (eventos == null || eventos.isEmpty()) {
            System.out.println("No se encontraron eventos para el asistente con ID: " + asistenteId);
        } else {
            System.out.println("Eventos encontrados para el asistente: " + eventos);
        }
        return eventos;
    }

    public void eliminarAsistenteDeEvento(Long eventoId, Long asistenteId) {
        Optional<Evento> evento = eventoRepository.findById(eventoId);
        if (evento.isPresent()) {
            Evento eventoExistente = evento.get();

            // Aquí cambiamos el acceso a `getAsistenteId()` en lugar de `getAsistente()`
            EventoAsistentes eventoAsistente = eventoExistente.getEventoAsistentes().stream()
                    .filter(ea -> ea.getAsistenteId().equals(asistenteId))  // Accedemos directamente a `asistenteId`
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Asistente no encontrado en este evento"));

            eventoExistente.removeEventoAsistente(eventoAsistente);
            eventoRepository.save(eventoExistente);
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

    public void agregarAsistenteAEvento(Long eventoId, Long asistenteId) {
        Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
        if (eventoOpt.isPresent()) {
            Evento evento = eventoOpt.get();

            // Verificar que el asistente exista
            Asistente asistente = asistenteClientRest.findById(asistenteId);

            // Crear la relación entre el evento y el asistente
            EventoAsistentes eventoAsistente = new EventoAsistentes();
            eventoAsistente.setAsistenteId(asistenteId);
            evento.addEventoAsistente(eventoAsistente);

            // Guardar el evento con el asistente asociado
            eventoRepository.save(evento);
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

    public List<Evento> obtenerEventosPorAsistente(Long asistenteId) {
        List<Evento> eventos = new ArrayList<>();

        // Obtener todos los eventos
        List<Evento> todosLosEventos = eventoRepository.findAll();

        // Filtrar los eventos en los que el asistente esté involucrado
        for (Evento evento : todosLosEventos) {
            for (EventoAsistentes eventoAsistente : evento.getEventoAsistentes()) {
                if (eventoAsistente.getAsistenteId().equals(asistenteId)) {
                    eventos.add(evento);
                    break;  // Si ya encontramos el evento, no seguimos buscando en este
                }
            }
        }

        if (eventos.isEmpty()) {
            throw new RuntimeException("No se encontraron eventos para el asistente con ID: " + asistenteId);
        }

        return eventos;
    }
}
