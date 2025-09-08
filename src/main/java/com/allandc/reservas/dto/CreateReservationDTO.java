package com.allandc.reservas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateReservationDTO(
        @NotNull(message = "Número da mesa a ser reservada obrigatório.")
        @Positive(message = "Número da mesa deve ser positivo.")
        Integer tableNumber,
        @NotNull(message = "Data da reserva obrigatória.")
        @Future(message = "Data da reserva deve ser no futuro.")
        LocalDateTime date,
        @NotNull(message = "Número de convidados obrigatório.")
        @Positive(message = "Número de convidados deve ser positivo.")
        Integer numberOfGuests) {
}
