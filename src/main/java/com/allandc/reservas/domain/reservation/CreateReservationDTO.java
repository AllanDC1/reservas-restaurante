package com.allandc.reservas.domain.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateReservationDTO(
        @Schema(description = "Número da mesa a ser reservada", example = "6")
        @NotNull(message = "Número da mesa a ser reservada obrigatório.")
        @Positive(message = "Número da mesa deve ser positivo.")
        Integer tableNumber,
        @Schema(description = "Data da reserva (no futuro) no formato ISO-8601", example = "2026-05-09T22:00:00")
        @NotNull(message = "Data da reserva obrigatória.")
        @Future(message = "Data da reserva deve ser no futuro.")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime date,
        @Schema(description = "Número de convidados", example = "4")
        @NotNull(message = "Número de convidados obrigatório.")
        @Positive(message = "Número de convidados deve ser positivo.")
        @Min(1)
        Integer numberOfGuests) {
}
