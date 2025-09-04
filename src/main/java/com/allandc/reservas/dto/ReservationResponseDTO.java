package com.allandc.reservas.dto;

import com.allandc.reservas.enums.ReservationStatus;

import java.time.LocalDateTime;


public record ReservationResponseDTO(int tableNumber, LocalDateTime date, ReservationStatus status) {
}
