package com.allandc.reservas.dto;

import com.allandc.reservas.enums.DiningTableStatus;

public record UpdateDiningTableDTO(Integer capacity, DiningTableStatus status) {
}
