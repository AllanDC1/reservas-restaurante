package com.allandc.reservas.dto;

import com.allandc.reservas.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "Nome obrigatório.")
        @Size(min = 3, max = 50, message = "Tamanho do nome inválido (03-50)")
        String name,
        @NotBlank(message = "Email obrigatório.")
        @Email(message = "Email inválido.")
        String email,
        @NotBlank(message = "Senha obrigatória.")
        @Size(min = 6, max = 50, message = "Tamanho da senha inválido (06-50)")
        String password,
        @NotNull(message = "Role do usuário obrigatório.")
        UserRoles role) {
}
