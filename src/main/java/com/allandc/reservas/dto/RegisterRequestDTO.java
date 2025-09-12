package com.allandc.reservas.dto;

import com.allandc.reservas.enums.UserRoles;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @Schema(description = "Nome do usuário", example = "Roberto Carlos")
        @NotBlank(message = "Nome obrigatório.")
        @Size(min = 3, max = 50, message = "Tamanho do nome inválido (03-50)")
        String name,
        @Schema(description = "Email do usuário", example = "roberto123@gmail.com")
        @NotBlank(message = "Email obrigatório.")
        @Email(message = "Email inválido.")
        String email,
        @Schema(description = "Senha para a conta, mínimo de 6 caracteres", example = "rob123")
        @NotBlank(message = "Senha obrigatória.")
        @Size(min = 6, max = 50, message = "Tamanho da senha inválido (06-50)")
        String password,
        @Schema(description = "Cargo da conta para permissões", example = "CUSTOMER")
        @NotNull(message = "Role do usuário obrigatório.")
        UserRoles role) {
}
