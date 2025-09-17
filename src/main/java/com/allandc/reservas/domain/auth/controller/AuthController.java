package com.allandc.reservas.domain.auth.controller;

import com.allandc.reservas.domain.auth.dto.LoginRequestDTO;
import com.allandc.reservas.domain.auth.dto.RegisterRequestDTO;
import com.allandc.reservas.domain.auth.dto.LoginResponseDTO;
import com.allandc.reservas.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Fazer login em conta já existente")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO login = userService.login(loginRequestDTO);
        if (login == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(login);
    }

    @Operation(summary = "Fazer registro de uma nova conta")
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {

        LoginResponseDTO register = userService.register(registerRequestDTO);
        if (register == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(register);
    }
}
