package com.furryfriends.FurryFriends_Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.furryfriends.FurryFriends_Backend.enums.StatusPet;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "raza", nullable = false)
    private String raza;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "info_medica")
    private String infoMedica;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pet", nullable = false)
    private StatusPet statusPet = StatusPet.Activa;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Se asignan automáticamente las fechas antes de guardar o actualizar
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
