package com.allandc.reservas.dto;

import java.util.Date;

public record ReservationResponseDTO(int tableNumber, Date date, String status) {
}
