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
@Table(name = "personal_access_tokens")
public class PersonalAccessToken {
    @Id
    @ColumnDefault("nextval('personal_access_tokens_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tokenable_type", nullable = false)
    private String tokenableType;

    @Column(name = "tokenable_id", nullable = false)
    private Long tokenableId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "token", nullable = false, length = 64)
    private String token;

    @Column(name = "abilities", length = Integer.MAX_VALUE)
    private String abilities;

    @Column(name = "last_used_at")
    private Instant lastUsedAt;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}