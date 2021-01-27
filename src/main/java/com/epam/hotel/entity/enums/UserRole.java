package com.epam.hotel.entity.enums;

public enum UserRole {
    GUEST("guest"),
    ADMINISTRATOR("administrator");

    UserRole(String role) {
        this.role = role;
    }

    private final String role;

    public String getRole() {
        return role;
    }
}
