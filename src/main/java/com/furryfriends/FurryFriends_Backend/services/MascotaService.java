package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.dto.MascotaDTO;
import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import com.furryfriends.FurryFriends_Backend.dto.UpdateMascota;
import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.enums.StatusPet;
import com.furryfriends.FurryFriends_Backend.repositories.MascotaRepository;
import com.furryfriends.FurryFriends_Backend.repositories.UserRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota findById(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Mascota no encontrada con id: " + id));
    }

    @Override
    public Boolean create(Mascota mascota) {
        try {
            if (mascota.getCreatedAt() == null) {
                mascota.setCreatedAt(Instant.now());
            }
            mascota.setUpdatedAt(Instant.now());
            mascotaRepository.save(mascota);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Mascota update(Long id, UpdateMascota dto) {
        Mascota existingMascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Mascota no encontrada con id: " + id));

        existingMascota.setNombre(dto.getNombre());
        existingMascota.setEdad(dto.getEdad());
        existingMascota.setRaza(dto.getRaza());
        existingMascota.setSexo(dto.getSexo());
        existingMascota.setColor(dto.getColor());
        existingMascota.setInfoMedica(dto.getInfoMedica());
        existingMascota.setUpdatedAt(Instant.now());  // Actualiza la fecha de modificación

        return mascotaRepository.save(existingMascota);
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

    @Override
    public Boolean updateStatus(Long id, StatusPet statusPet) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        if (mascota.isPresent()) {
            Mascota mascotaToUpdate = mascota.get();
            mascotaToUpdate.setStatusPet(statusPet);
            mascotaRepository.save(mascotaToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public Boolean save(Mascota mascota) {
        try {
            mascotaRepository.save(mascota);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Mascota registrarMascota(MascotaDTO mascotaDTO, Long usuarioId) {
        // Verificar que el usuario existe y tiene rol propietario
        User user = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getRole().equals("propietario")) {
            throw new RuntimeException("El usuario no tiene el rol de propietario");
        }

        // Crear la mascota y asociarla con el usuario
        Mascota mascota = new Mascota();
        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setEdad(mascotaDTO.getEdad());
        mascota.setRaza(mascotaDTO.getRaza());
        mascota.setSexo(mascotaDTO.getSexo());
        mascota.setColor(mascotaDTO.getColor());
        mascota.setInfoMedica(mascotaDTO.getInfoMedica());
        mascota.setUser(user);

        return mascotaRepository.save(mascota);
    }
}
