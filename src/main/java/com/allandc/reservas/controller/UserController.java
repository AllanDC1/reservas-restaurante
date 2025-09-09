package com.allandc.reservas.controller;

import com.allandc.reservas.dto.UserResponseDTO;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar todas as contas do sistema",
            security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping
    public List<UserResponseDTO> getUsers() {
        List<User> result = userService.getAllUsers();

        return result.stream()
                .map(u -> new UserResponseDTO(
                        u.getName(),
                        u.getEmail(),
                        u.getRole()))
                .toList();
    }

    // aberto para implementação de outros endpoints

}
