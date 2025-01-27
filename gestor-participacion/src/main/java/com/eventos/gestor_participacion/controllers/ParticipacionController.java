package com.eventos.gestor_participacion.controllers;

import com.eventos.gestor_eventos.models.entities.Participacion;
import com.eventos.gestor_eventos.services.ParticipacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participaciones")
public class ParticipacionController {
    private final ParticipacionService participacionService;

    public ParticipacionController(ParticipacionService participacionService) {
        this.participacionService = participacionService;
    }

    // Registrar una participación
    @PostMapping
    public ResponseEntity<Participacion> registrarParticipacion(@RequestBody Participacion participacion) {
        Participacion nuevaParticipacion = participacionService.registrarParticipacion(participacion);
        return ResponseEntity.ok(nuevaParticipacion);
    }

    // Cancelar una participación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarParticipacion(@PathVariable Long id) {
        participacionService.cancelarParticipacion(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos los asistentes de un evento
    @GetMapping("/evento/{eventoId}")
    public List<Participacion> listarAsistentesDeEvento(@PathVariable Long eventoId) {
        return participacionService.listarAsistentesDeEvento(eventoId);
    }

    // Listar todos los eventos de un asistente
    @GetMapping("/asistente/{asistenteId}")
    public List<Participacion> listarEventosDeAsistente(@PathVariable Long asistenteId) {
        return participacionService.listarEventosDeAsistente(asistenteId);
    }
}
