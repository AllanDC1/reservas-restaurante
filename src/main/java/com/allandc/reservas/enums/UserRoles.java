package com.allandc.reservas.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("admin"),
    CUSTOMER("customer");

    UserRoles(String role) {
    }
}
