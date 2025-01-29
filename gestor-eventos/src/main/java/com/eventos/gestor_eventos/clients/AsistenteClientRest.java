package com.eventos.gestor_eventos.clients;

import com.eventos.gestor_eventos.models.Asistente;
import com.eventos.gestor_eventos.models.entities.Evento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "asistentes", url = "http://localhost:8011/api/asistentes")
public interface AsistenteClientRest {

    @GetMapping("/{id}")
    Asistente findById(@PathVariable Long id);

    @GetMapping
    List<Asistente> findAll();

    @PostMapping
    Asistente save(@RequestBody Asistente asistente);

    @GetMapping("/{id}/eventos")
    List<Evento> findEventosByAsistenteId(@PathVariable Long id);
}
