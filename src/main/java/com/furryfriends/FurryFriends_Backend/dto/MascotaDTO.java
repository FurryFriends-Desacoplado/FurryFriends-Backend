package com.furryfriends.FurryFriends_Backend.dto;

import com.furryfriends.FurryFriends_Backend.enums.StatusPet;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MascotaDTO {
    private Long id;
    private String nombre;
    private int edad;
    private String raza;
    private String sexo;
    private String color;
    private String infoMedica;
    private StatusPet statusPet;
    private Instant createdAt;
    private Instant updatedAt;
    private UserDTO user;  // DTO de usuario
}
