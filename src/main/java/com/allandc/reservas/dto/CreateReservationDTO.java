package com.allandc.reservas.dto;

import java.time.LocalDateTime;

public record CreateReservationDTO(int tableNumber, LocalDateTime date, int numberOfGuests) {
}
