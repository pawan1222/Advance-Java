package com.example.Online_Course_Management_Work.service;

import com.example.Online_Course_Management_Work.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO getById(Long id);
}

