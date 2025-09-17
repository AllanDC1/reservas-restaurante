package com.allandc.reservas.domain.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @Schema(description = "Email da conta", example = "roberto123@gmail.com")
        @NotBlank(message = "Email obrigatório")
        @Email(message = "Email inválido.")
        String email,
        @Schema(description = "Senha da conta", example = "rob123")
        @NotBlank(message = "Senha obrigatória")
        String password) {
}
