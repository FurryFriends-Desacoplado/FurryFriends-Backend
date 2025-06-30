package com.furryfriends.FurryFriends_Backend.entities;

import com.furryfriends.FurryFriends_Backend.enums.StatusPet;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ColumnDefault("nextval('mascotas_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)  // Cambiado a EAGER
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "user_id", nullable = false)
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

    @Column(name = "info_medica", nullable = true)
    private String infoMedica;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pet")
    private StatusPet statusPet;  // Asegúrate de que se mapea correctamente

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getInfoMedica() {
        return infoMedica;
    }

    public void setInfoMedica(String infoMedica) {
        this.infoMedica = infoMedica;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
