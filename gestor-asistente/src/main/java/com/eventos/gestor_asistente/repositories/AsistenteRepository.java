package com.eventos.gestor_asistente.repositories;

import com.eventos.gestor_asistente.models.entities.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    // Definir el método para buscar por email
    Optional<Asistente> findByEmail(String email);
}


