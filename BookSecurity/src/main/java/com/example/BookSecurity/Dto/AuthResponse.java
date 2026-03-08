package com.example.BookSecurity.Dto;

import lombok.Data;

@Data
public class AuthResponse {
    String token;
    String username;
    String role;

    public AuthResponse(String token, String username, String name) {
    }
}
