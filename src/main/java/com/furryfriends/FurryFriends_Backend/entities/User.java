package com.furryfriends.FurryFriends_Backend.entities;

import com.furryfriends.FurryFriends_Backend.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "experiencia", length = 65535) // Usar 65535 para TEXT en MySQL
    private String experiencia;

    @Column(name = "numero_mascotas")
    private Integer numeroMascotas;

    @Enumerated(EnumType.STRING)
    @Column(name = "enrollment_status", columnDefinition = "enrollment_status_enum default 'Activa'", insertable = false, updatable = false)
    private EnrollmentStatus enrollmentStatus;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}