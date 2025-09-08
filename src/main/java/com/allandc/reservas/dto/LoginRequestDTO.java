package com.allandc.reservas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Email obrigatório")
        @Email(message = "Email inválido.")
        String email,
        @NotBlank(message = "Senha obrigatória")
        String password) {
}
