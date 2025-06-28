package com.furryfriends.FurryFriends_Backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateUser {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String direccion;
    private String tipoDocumento;
    private String numeroDocumento;
    private String password;
    private String role;
    private String fotoPerfil;
    private String experiencia;
    private Integer numeroMascotas;
}
