package com.eventos.gestor_asistente.controllers;

import com.eventos.gestor_asistente.models.entities.Asistente;
import com.eventos.gestor_asistente.services.AsistenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistentes")
public class AsistenteController {

    private final AsistenteService asistenteService;

    public AsistenteController(AsistenteService asistenteService) {
        this.asistenteService = asistenteService;
    }

    @PostMapping
    public ResponseEntity<Asistente> crearAsistente(@Valid @RequestBody Asistente asistente) {
        return ResponseEntity.ok(asistenteService.guardarAsistente(asistente));
    }

    @GetMapping
    public ResponseEntity<List<Asistente>> obtenerTodosLosAsistentes() {
        return ResponseEntity.ok(asistenteService.obtenerTodosAsistentes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistente> obtenerAsistentePorId(@PathVariable Long id) {
        Optional<Asistente> asistente = asistenteService.obtenerAsistentePorId(id);
        return asistente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistente> actualizarAsistente(@PathVariable Long id, @Valid @RequestBody Asistente asistente) {
        Optional<Asistente> asistenteActualizado = asistenteService.actualizarAsistente(id, asistente);
        return asistenteActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsistente(@PathVariable Long id) {
        if (asistenteService.eliminarAsistente(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
