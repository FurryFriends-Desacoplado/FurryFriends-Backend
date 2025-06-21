package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import com.furryfriends.FurryFriends_Backend.repositories.MascotaRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota findById(Long id) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        return mascota.orElse(null);
    }

    @Override
    public Boolean create(Mascota mascota) {
        try {
            mascotaRepository.save(mascota);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Mascota mascota) {
        try {
            mascotaRepository.save(mascota);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Mascota mascota) {
        try {
            mascotaRepository.delete(mascota);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
