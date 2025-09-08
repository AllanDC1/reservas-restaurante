package com.allandc.reservas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDiningTableDTO(
        @NotNull(message = "Número da mesa obrigatório.")
        @Positive(message = "Número da mesa deve ser positivo.")
        Integer number,
        @NotNull(message = "Capacidade da mesa obrigatório.")
        @Positive(message = "Capacidade da mesa deve ser positivo.")
        Integer capacity) {
}
