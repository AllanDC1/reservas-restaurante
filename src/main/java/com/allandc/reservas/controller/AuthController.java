package com.allandc.reservas.controller;

import com.allandc.reservas.dto.LoginRequestDTO;
import com.allandc.reservas.dto.RegisterRequestDTO;
import com.allandc.reservas.dto.UserResponseDTO;
import com.allandc.reservas.repository.UserRepository;
import com.allandc.reservas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        UserResponseDTO login = userService.login(loginRequestDTO);
        if (login == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(login);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {

        UserResponseDTO register = userService.register(registerRequestDTO);
        if (register == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(register);
    }
}
