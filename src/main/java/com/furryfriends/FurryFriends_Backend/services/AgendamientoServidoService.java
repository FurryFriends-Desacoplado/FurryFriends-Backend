package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.AgendamientoServido;
import com.furryfriends.FurryFriends_Backend.repositories.AgendamientoServidoRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IAgendamientoServidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamientoServidoService implements IAgendamientoServidoService {

    @Autowired
    private AgendamientoServidoRepository agendamientoServidoRepository;

    @Override
    public List<AgendamientoServido> findAll() {
        return agendamientoServidoRepository.findAll();
    }

    @Override
    public AgendamientoServido findById(Long id) {
        Optional<AgendamientoServido> agendamientoServido = agendamientoServidoRepository.findById(id);
        return agendamientoServido.orElse(null);  // Devuelve null si no encuentra el agendamiento servido
    }

    @Override
    public Boolean create(AgendamientoServido agendamientoServido) {
        try {
            agendamientoServidoRepository.save(agendamientoServido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(AgendamientoServido agendamientoServido) {
        try {
            agendamientoServidoRepository.save(agendamientoServido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(AgendamientoServido agendamientoServido) {
        try {
            agendamientoServidoRepository.delete(agendamientoServido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
