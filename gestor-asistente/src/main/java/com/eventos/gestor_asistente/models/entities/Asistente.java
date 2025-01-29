package com.eventos.gestor_asistente.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Asistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo 'nombre' es obligatorio y no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "El campo 'email' es obligatorio y no puede estar vacío.")
    @Email(message = "El campo 'email' debe tener un formato válido.")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Constructor vacío
    public Asistente() {
    }

    // Constructor con argumentos (opcional)
    public Asistente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}
