package com.allandc.reservas.domain.user.dto;

import com.allandc.reservas.domain.user.UserRoles;

public record UserResponseDTO(String name, String email, UserRoles role) {
}
