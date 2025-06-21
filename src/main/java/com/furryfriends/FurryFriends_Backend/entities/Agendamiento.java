package com.furryfriends.FurryFriends_Backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "agendamientos")
public class Agendamiento {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "fechaagendamiento", nullable = false)
    private LocalDate fechaagendamiento;

    @Column(name = "tiposervicio", nullable = false)
    private String tiposervicio;

    @Column(name = "estadoagendamiento", nullable = false)
    private String estadoagendamiento;

    @Column(name = "id_propietario")
    private String idPropietario;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "trial936", length = Integer.MAX_VALUE)
    private String trial936;

}