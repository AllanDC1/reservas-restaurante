package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateUserDTO;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.repository.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserDTO dto) {

        User newUser = new User();

        newUser.setName(dto.name());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setRole(dto.role());

        return repository.save(newUser);
    }
}
