package com.furryfriends.FurryFriends_Backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMascota {
    private String nombre;
    private int edad;
    private String raza;
    private String sexo;
    private String color;
    private String infoMedica;
}
