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

    public Asistente guardarAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    public List<Asistente> obtenerTodosAsistentes() {
        return asistenteRepository.findAll();
    }

    public Optional<Asistente> obtenerAsistentePorId(Long id) {
        return asistenteRepository.findById(id);
    }

    public Optional<Asistente> actualizarAsistente(Long id, Asistente asistente) {
        return asistenteRepository.findById(id).map(asistenteExistente -> {
            asistenteExistente.setNombre(asistente.getNombre());
            asistenteExistente.setEmail(asistente.getEmail());
            return asistenteRepository.save(asistenteExistente);
        });
    }

    public boolean eliminarAsistente(Long id) {
        if (asistenteRepository.existsById(id)) {
            asistenteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
