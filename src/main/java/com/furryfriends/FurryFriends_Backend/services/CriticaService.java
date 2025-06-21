package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Critica;
import com.furryfriends.FurryFriends_Backend.repositories.CriticaRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.ICriticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriticaService implements ICriticaService {

    @Autowired
    private CriticaRepository criticaRepository;

    @Override
    public List<Critica> findAll() {
        return criticaRepository.findAll();
    }

    @Override
    public Critica findById(Long id) {
        Optional<Critica> critica = criticaRepository.findById(id);
        return critica.orElse(null);  // Devuelve null si no encuentra la crítica
    }

    @Override
    public Boolean create(Critica critica) {
        try {
            criticaRepository.save(critica);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Critica critica) {
        try {
            criticaRepository.save(critica);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Critica critica) {
        try {
            criticaRepository.delete(critica);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
