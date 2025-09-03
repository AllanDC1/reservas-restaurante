package com.allandc.reservas.dto;

import com.allandc.reservas.enums.UserRoles;

public record RegisterRequestDTO(String name, String email, String password, UserRoles role) {
}
