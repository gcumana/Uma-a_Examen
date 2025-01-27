package com.eventos.gestor_asistente.services;


import com.eventos.gestor_asistente.models.entities.Asistente;
import com.eventos.gestor_asistente.repositories.AsistenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenteService {



    private final AsistenteRepository asistenteRepository;

    public AsistenteService(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    // Método para guardar un nuevo asistente
    public Asistente guardarAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    // Método para obtener todos los asistentes
    public List<Asistente> obtenerTodosAsistentes() {
        return asistenteRepository.findAll();
    }

    // Método para obtener un asistente por su ID
    public Optional<Asistente> obtenerAsistentePorId(Long id) {
        return asistenteRepository.findById(id);
    }

    // Método para eliminar un asistente por su ID
    public void eliminarAsistente(Long id) {
        asistenteRepository.deleteById(id);
    }

    // Método para buscar asistentes por email (opcional)
    public Optional<Asistente> obtenerAsistentePorEmail(String email) {
        return asistenteRepository.findByEmail(email);
    }
}
