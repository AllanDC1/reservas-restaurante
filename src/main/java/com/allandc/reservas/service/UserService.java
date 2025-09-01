package com.allandc.reservas.service;

import com.allandc.reservas.dto.CreateUserDTO;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.repository.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User createUser(CreateUserDTO data) {

        User newUser = new User();

        newUser.setName(data.name());
        newUser.setEmail(data.email());
        // alterar quando implementar criptografia
        newUser.setPassword(data.password());
        newUser.setRole(data.role());

        return repository.save(newUser);
    }
}
