package com.allandc.reservas.controller;

import com.allandc.reservas.dto.CreateUserDTO;
import com.allandc.reservas.dto.UserResponseDTO;
import com.allandc.reservas.entity.User;
import com.allandc.reservas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO registerUser(@RequestBody CreateUserDTO userDTO) {
        User tempUser = userService.createUser(userDTO);

        return new UserResponseDTO(tempUser.getName(), tempUser.getEmail(), tempUser.getRole());
    }

    // @PostMapping("/login") implementar
}
