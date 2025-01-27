package com.eventos.gestor_participacion.services;


import com.eventos.gestor_eventos.models.entities.Participacion;
import com.eventos.gestor_eventos.repositories.ParticipacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParticipacionService {
    private final ParticipacionRepository participacionRepository;

    public ParticipacionService(ParticipacionRepository participacionRepository) {
        this.participacionRepository = participacionRepository;
    }

    public Participacion registrarParticipacion(Participacion participacion) {
        participacion.setFechaRegistro(LocalDateTime.now());
        return participacionRepository.save(participacion);
    }

    public void cancelarParticipacion(Long id) {
        participacionRepository.deleteById(id);
    }

    public List<Participacion> listarAsistentesDeEvento(Long eventoId) {
        return participacionRepository.findByEventoId(eventoId);
    }

    public List<Participacion> listarEventosDeAsistente(Long asistenteId) {
        return participacionRepository.findByAsistenteId(asistenteId);
    }
}

