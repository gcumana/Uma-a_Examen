package com.eventos.gestor_eventos.controllers;

import com.eventos.gestor_eventos.models.Asistente;
import com.eventos.gestor_eventos.models.entities.Evento;
import com.eventos.gestor_eventos.services.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> listarEventos() {
        return eventoService.listarEventos();
    }

    @PostMapping
    public Evento crearEvento(@RequestBody Evento evento) {
        return eventoService.guardarEvento(evento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Long id) {
        return eventoService.obtenerEventoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.obtenerEventoPorId(id)
                .map(eventoExistente -> {
                    evento.setId(id);
                    Evento eventoActualizado = eventoService.guardarEvento(evento);
                    return ResponseEntity.ok(eventoActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        try {
            eventoService.eliminarEvento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/asistentes")
    public ResponseEntity<List<Asistente>> listarAsistentes(@PathVariable Long id) {
        try {
            List<Asistente> asistentes = eventoService.listarAsistentesDeEvento(id);
            return ResponseEntity.ok(asistentes);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/asistentes/{id}/eventos")
    public ResponseEntity<List<Evento>> listarEventosPorAsistente(@PathVariable Long id) {
        try {
            List<Evento> eventos = eventoService.listarEventosDeAsistente(id);
            return ResponseEntity.ok(eventos);
        } catch (RuntimeException e) {
            System.out.println("Error al obtener eventos para el asistente: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{eventoId}/asistentes/{asistenteId}")
    public ResponseEntity<Void> eliminarAsistente(@PathVariable Long eventoId, @PathVariable Long asistenteId) {
        try {
            eventoService.eliminarAsistenteDeEvento(eventoId, asistenteId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{eventoId}/asistentes/{asistenteId}")
    public ResponseEntity<Void> agregarAsistente(@PathVariable Long eventoId, @PathVariable Long asistenteId) {
        try {
            eventoService.agregarAsistenteAEvento(eventoId, asistenteId);
            return ResponseEntity.status(201).build(); // Status 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Cambiar la ruta para uno de los métodos
    @GetMapping("/asistentes/{id}/eventos/listado")
    public ResponseEntity<List<Evento>> obtenerEventosDeAsistente(@PathVariable Long id) {
        try {
            List<Evento> eventos = eventoService.obtenerEventosPorAsistente(id);
            return ResponseEntity.ok(eventos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
