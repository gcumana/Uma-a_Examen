package com.eventos.gestor_participacion.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    @NotNull(message = "El campo 'evento' es obligatorio y no puede ser nulo.")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "asistente_id")
    @NotNull(message = "El campo 'asistente' es obligatorio y no puede ser nulo.")
    private Asistente asistente;

    private LocalDateTime fechaRegistro;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Constructor vacío
    public Participacion() {
    }

    // Constructor con argumentos (opcional)
    public Participacion(Evento evento, Asistente asistente, LocalDateTime fechaRegistro) {
        this.evento = evento;
        this.asistente = asistente;
        this.fechaRegistro = fechaRegistro;
    }
}
