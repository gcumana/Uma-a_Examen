package com.eventos.gestor_eventos.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "eventos_asistentes")
public class EventoAsistentes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asistente_id")
    private Long asistenteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAsistenteId() {
        return asistenteId;
    }

    public void setAsistenteId(Long asistenteId) {
        this.asistenteId = asistenteId;
    }
}
