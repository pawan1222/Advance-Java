package com.example.Online_Course_Management_Work.service;

import com.example.Online_Course_Management_Work.dto.AuthResponseDTO;
import com.example.Online_Course_Management_Work.dto.LoginRequestDTO;
import com.example.Online_Course_Management_Work.dto.RegisterRequestDTO;
import com.example.Online_Course_Management_Work.dto.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);
}

