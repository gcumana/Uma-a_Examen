package com.eventos.gestor_eventos.models.entities;

import com.eventos.gestor_eventos.models.Asistente;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private String ubicacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "evento_id")
    private List<EventoAsistentes> eventoAsistentes;

    @Transient
    private List<Asistente> asistentes;

    public Evento() {
        eventoAsistentes = new ArrayList<>();
        asistentes = new ArrayList<>();
    }

    public Evento(String nombre, String fecha, String ubicacion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        eventoAsistentes = new ArrayList<>();
        asistentes = new ArrayList<>();
    }

    public void addEventoAsistente(EventoAsistentes eventoAsistente) {
        if (eventoAsistente == null) {
            throw new IllegalArgumentException("EventoAsistente no puede ser nulo");
        }
        eventoAsistentes.add(eventoAsistente);
    }

    public void removeEventoAsistente(EventoAsistentes eventoAsistente) {
        if (eventoAsistente == null) {
            throw new IllegalArgumentException("EventoAsistente no puede ser nulo");
        }
        eventoAsistentes.remove(eventoAsistente);
    }

    public List<Asistente> getAsistentes(List<Asistente> allAsistentes) {
        if (allAsistentes == null) {
            throw new IllegalArgumentException("La lista de asistentes no puede ser nula");
        }

        List<Asistente> asistentesList = new ArrayList<>();
        for (EventoAsistentes eventoAsistente : eventoAsistentes) {
            if (eventoAsistente == null) {
                continue;
            }
            for (Asistente asistente : allAsistentes) {
                if (asistente == null) {
                    continue;
                }
                if (Objects.equals(asistente.getId(), eventoAsistente.getAsistenteId())) {
                    asistentesList.add(asistente);
                }
            }
        }
        return asistentesList;
    }


    public List<EventoAsistentes> getEventoAsistentes() {
        return eventoAsistentes;
    }

    // Getters and Setters with validation
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del evento debe ser positivo y no nulo");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del evento no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.isEmpty()) {
            throw new IllegalArgumentException("La fecha del evento no puede ser nula o vacía");
        }
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        if (ubicacion == null || ubicacion.isEmpty()) {
            throw new IllegalArgumentException("La ubicación del evento no puede ser nula o vacía");
        }
        this.ubicacion = ubicacion;
    }
}
