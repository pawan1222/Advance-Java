package com.example.BookSecurity.Entity;

public enum Role {
    USER,
    ADMIN;

    public static Role from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Role value cannot be null or blank");
        }
        return Role.valueOf(value.trim().toUpperCase());
    }
}
