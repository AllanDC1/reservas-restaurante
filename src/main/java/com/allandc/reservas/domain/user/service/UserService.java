package com.allandc.reservas.domain.user.service;

import com.allandc.reservas.domain.auth.dto.LoginRequestDTO;
import com.allandc.reservas.domain.auth.dto.LoginResponseDTO;
import com.allandc.reservas.domain.auth.dto.RegisterRequestDTO;
import com.allandc.reservas.domain.user.User;
import com.allandc.reservas.domain.user.repository.UserRepository;
import com.allandc.reservas.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public LoginResponseDTO register(RegisterRequestDTO dto) {

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

        return new LoginResponseDTO(newUser.getName(), token);
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = repository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Senha inválida.");
        }

        String token = tokenService.generateToken(user);
        String name = tokenService.extractName(token);

        return new LoginResponseDTO(name, token);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
