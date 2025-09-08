package com.allandc.reservas.service;

import com.allandc.reservas.dto.LoginRequestDTO;
import com.allandc.reservas.dto.UserResponseDTO;
import com.allandc.reservas.dto.RegisterRequestDTO;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.repository.UserRepository;
import com.allandc.reservas.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public UserResponseDTO register(RegisterRequestDTO dto) {

        Optional<User> tempUser = repository.findByEmail(dto.email());

        if (tempUser.isPresent()) {
            throw new RuntimeException("Email já registrado.");
        }

        User newUser = new User();

        newUser.setName(dto.name());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setRole(dto.role());

        repository.save(newUser);

        String token = tokenService.generateToken(newUser);

        return new UserResponseDTO(newUser.getName(), token);
    }

    public UserResponseDTO login(LoginRequestDTO dto) {
        User user = repository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Senha inválida.");
        }

        String token = tokenService.generateToken(user);
        String name = tokenService.extractName(token);

        return new UserResponseDTO(name, token);
    }
}
