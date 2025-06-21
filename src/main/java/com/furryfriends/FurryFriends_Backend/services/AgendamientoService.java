package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Agendamiento;
import com.furryfriends.FurryFriends_Backend.repositories.AgendamientoRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IAgendamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamientoService implements IAgendamientoService {

    @Autowired
    private AgendamientoRepository agendamientoRepository;

    @Override
    public List<Agendamiento> findAll() {
        return agendamientoRepository.findAll();
    }

    @Override
    public Agendamiento findById(String id) {
        Optional<Agendamiento> agendamiento = agendamientoRepository.findById(id);
        return agendamiento.orElse(null);  // Devuelve null si no encuentra el agendamiento
    }

    @Override
    public Boolean create(Agendamiento agendamiento) {
        try {
            agendamientoRepository.save(agendamiento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Agendamiento agendamiento) {
        try {
            agendamientoRepository.save(agendamiento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Agendamiento agendamiento) {
        try {
            agendamientoRepository.delete(agendamiento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
