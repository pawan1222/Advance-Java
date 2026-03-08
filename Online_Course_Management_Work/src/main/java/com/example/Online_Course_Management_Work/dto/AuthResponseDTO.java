package com.example.Online_Course_Management_Work.dto;

import com.example.Online_Course_Management_Work.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private Long userId;
    private String fullName;
    private String email;
    private Role role;
    private String profilePicture;
    private String message;
}

