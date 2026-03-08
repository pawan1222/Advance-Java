package com.example.Online_Course_Management_Work.controller;

import com.example.Online_Course_Management_Work.dto.AuthResponseDTO;
import com.example.Online_Course_Management_Work.dto.LoginRequestDTO;
import com.example.Online_Course_Management_Work.dto.RegisterRequestDTO;
import com.example.Online_Course_Management_Work.dto.UserResponseDTO;
import com.example.Online_Course_Management_Work.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

