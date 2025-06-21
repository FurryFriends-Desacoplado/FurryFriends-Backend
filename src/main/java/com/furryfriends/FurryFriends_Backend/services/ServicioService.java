package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Servicio;
import com.furryfriends.FurryFriends_Backend.repositories.ServicioRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio findById(Long id) {
        Optional<Servicio> servicio = servicioRepository.findById(id);
        return servicio.orElse(null);
    }

    @Override
    public Boolean create(Servicio servicio) {
        try {
            servicioRepository.save(servicio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Servicio servicio) {
        try {
            servicioRepository.save(servicio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Servicio servicio) {
        try {
            servicioRepository.delete(servicio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
