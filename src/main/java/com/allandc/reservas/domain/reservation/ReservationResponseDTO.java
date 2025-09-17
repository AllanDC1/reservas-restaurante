package com.allandc.reservas.domain.reservation;

import java.time.LocalDateTime;


public record ReservationResponseDTO(int tableNumber, LocalDateTime date, ReservationStatus status) {
}
