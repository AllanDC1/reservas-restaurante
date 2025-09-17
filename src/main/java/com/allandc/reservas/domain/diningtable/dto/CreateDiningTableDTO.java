package com.allandc.reservas.domain.diningtable.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDiningTableDTO(
        @Schema(description = "Número da mesa", example = "6")
        @NotNull(message = "Número da mesa obrigatório.")
        @Positive(message = "Número da mesa deve ser positivo.")
        @Min(1)
        Integer number,
        @Schema(description = "Capacidade da mesa", example = "4")
        @NotNull(message = "Capacidade da mesa obrigatório.")
        @Positive(message = "Capacidade da mesa deve ser positivo.")
        @Min(1)
        Integer capacity) {
}
