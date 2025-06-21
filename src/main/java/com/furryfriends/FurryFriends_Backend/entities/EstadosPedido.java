package com.furryfriends.FurryFriends_Backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "estados_pedidos")
public class EstadosPedido {
    @Id
    @ColumnDefault("nextval('estados_pedidos_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("'pendiente'")
    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "trial932", length = Integer.MAX_VALUE)
    private String trial932;

}