package com.furryfriends.FurryFriends_Backend.dto;

import com.furryfriends.FurryFriends_Backend.enums.EnrollmentStatus;

public class UserDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String tipoDocumento;
    private String numeroDocumento;
    private String direccion;
    private String fotoPerfil;
    private String experiencia;
    private EnrollmentStatus enrollmentStatus; // 👈 Campo agregado y corregido

    // Constructor vacío
    public UserDTO() {
    }

    // Constructor con parámetros
    public UserDTO(Long id, String nombre, String apellido, String telefono, String email,
                   String tipoDocumento, String numeroDocumento, String direccion,
                   String fotoPerfil, String experiencia, EnrollmentStatus enrollmentStatus) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.direccion = direccion;
        this.fotoPerfil = fotoPerfil;
        this.experiencia = experiencia;
        this.enrollmentStatus = enrollmentStatus;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
}
