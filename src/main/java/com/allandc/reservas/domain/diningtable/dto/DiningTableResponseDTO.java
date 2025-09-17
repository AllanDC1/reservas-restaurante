package com.allandc.reservas.domain.diningtable.dto;

import com.allandc.reservas.domain.diningtable.DiningTableStatus;

public record DiningTableResponseDTO(Integer number, Integer capacity, DiningTableStatus status) {
}
