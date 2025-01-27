package com.eventos.gestor_asistente.controllers;


import com.eventos.gestor_asistente.models.entities.Asistente;
import com.eventos.gestor_asistente.services.AsistenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistentes")
public class AsistenteController {

    private final AsistenteService asistenteService;

    public AsistenteController(AsistenteService asistenteService) {
        this.asistenteService = asistenteService;
    }

    @PostMapping
    public ResponseEntity<Asistente> crearAsistente(@Valid @RequestBody Asistente asistente) {
        return ResponseEntity.ok(asistenteService.guardarAsistente(asistente));
    }

    // Otros métodos...
}
