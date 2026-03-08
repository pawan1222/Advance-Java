package com.example.BookSecurity.Dto;

import lombok.Data;

@Data
public class RegisterRequest {
    String username;
    String password;
    String role;
}
