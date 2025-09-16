package com.allandc.reservas.domain.user;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("admin"),
    CUSTOMER("customer");

    UserRoles(String role) {
    }
}
