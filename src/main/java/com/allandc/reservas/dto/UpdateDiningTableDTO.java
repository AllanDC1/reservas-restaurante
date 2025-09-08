package com.allandc.reservas.dto;

import com.allandc.reservas.enums.DiningTableStatus;
import jakarta.validation.constraints.Positive;

public record UpdateDiningTableDTO(
        @Positive(message = "Número da mesa deve ser positivo.")
        Integer capacity,
        @Positive(message = "Capacidade da mesa deve ser positivo.")
        DiningTableStatus status) {
}
