package com.eventos.gestor_eventos.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo 'nombre' es obligatorio y no puede estar vacío.")
    private String nombre;

    @NotNull(message = "El campo 'fecha' es obligatorio y no puede ser nulo.")
    private LocalDateTime fecha;

    @NotBlank(message = "El campo 'ubicación' es obligatorio y no puede estar vacío.")
    private String ubicacion;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Constructor vacío
    public Evento() {
    }

    // Constructor con argumentos (opcional)
    public Evento(String nombre, LocalDateTime fecha, String ubicacion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }
}
