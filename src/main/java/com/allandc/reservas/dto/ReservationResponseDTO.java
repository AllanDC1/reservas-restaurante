package com.allandc.reservas.dto;

import com.allandc.reservas.enums.ReservationStatus;

import java.util.Date;

public record ReservationResponseDTO(int tableNumber, Date date, ReservationStatus status) {
}
