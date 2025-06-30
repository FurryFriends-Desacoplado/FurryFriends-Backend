package com.furryfriends.FurryFriends_Backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusPet {
    Activa,
    Inactiva;

    @JsonCreator
    public static StatusPet fromString(String value) {
        // Convierte el valor a mayúsculas y luego lo compara con los valores del enum
        try {
            return StatusPet.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado de mascota inválido: " + value);
        }
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
