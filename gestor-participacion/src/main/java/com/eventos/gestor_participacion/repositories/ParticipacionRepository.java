package com.eventos.gestor_participacion.repositories;



import com.eventos.gestor_eventos.models.entities.Participacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipacionRepository extends JpaRepository<Participacion, Long> {
    List<Participacion> findByEventoId(Long eventoId);
    List<Participacion> findByAsistenteId(Long asistenteId);
}

