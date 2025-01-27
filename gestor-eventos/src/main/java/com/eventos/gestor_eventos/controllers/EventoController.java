package com.eventos.gestor_eventos.controllers;


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

    @DeleteMapping("/{id}")
    public void eliminarEvento(@PathVariable Long id) {
        eventoService.eliminarEvento(id);
    }
}
