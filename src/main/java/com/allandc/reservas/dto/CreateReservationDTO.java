package com.allandc.reservas.dto;

import java.util.Date;

public record CreateReservationDTO(int tableId, Date date, int numberOfGuests) {
}
