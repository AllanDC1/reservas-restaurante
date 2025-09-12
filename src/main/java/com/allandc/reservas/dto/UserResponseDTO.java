package com.allandc.reservas.dto;

import com.allandc.reservas.enums.UserRoles;

public record UserResponseDTO(String name, String email, UserRoles role) {
}
