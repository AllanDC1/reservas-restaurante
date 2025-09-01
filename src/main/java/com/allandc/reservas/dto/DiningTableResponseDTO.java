package com.allandc.reservas.dto;

import com.allandc.reservas.enums.DiningTableStatus;

public record DiningTableResponseDTO(Integer number, Integer capacity, DiningTableStatus status) {
}
