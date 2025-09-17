package com.allandc.reservas.domain.reservation.dto;

import com.allandc.reservas.domain.reservation.ReservationStatus;

import java.time.LocalDateTime;


public record ReservationResponseDTO(int tableNumber, LocalDateTime date, ReservationStatus status) {
}
