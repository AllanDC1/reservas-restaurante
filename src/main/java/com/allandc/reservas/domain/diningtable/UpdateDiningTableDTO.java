package com.allandc.reservas.domain.diningtable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record UpdateDiningTableDTO(
        @Schema(description = "Nova capacidade para a mesa", example = "8")
        @Positive(message = "Capacidade da mesa deve ser positivo.")
        Integer capacity,
        @Schema(description = "Novo status para a mesa", example = "INACTIVE")
        DiningTableStatus status) {
}
