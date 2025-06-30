package com.furryfriends.FurryFriends_Backend.mappers;
import com.furryfriends.FurryFriends_Backend.dto.MascotaDTO;
import com.furryfriends.FurryFriends_Backend.dto.UserDTO;
import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import com.furryfriends.FurryFriends_Backend.entities.User;

public class MascotaMapper {

    public static MascotaDTO toDto(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEdad(mascota.getEdad());
        dto.setRaza(mascota.getRaza());
        dto.setSexo(mascota.getSexo());
        dto.setColor(mascota.getColor());
        dto.setInfoMedica(mascota.getInfoMedica());
        dto.setStatusPet(mascota.getStatusPet());
        dto.setCreatedAt(mascota.getCreatedAt());
        dto.setUpdatedAt(mascota.getUpdatedAt());

        // Mapeo del usuario
        User user = mascota.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNombre(user.getNombre());
        userDTO.setApellido(user.getApellido());
        userDTO.setTelefono(user.getTelefono());
        userDTO.setEmail(user.getEmail());


        dto.setUser(userDTO);
        return dto;
    }
}


