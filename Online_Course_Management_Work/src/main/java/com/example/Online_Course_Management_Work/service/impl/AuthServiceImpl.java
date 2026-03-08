package com.example.Online_Course_Management_Work.service.impl;

import com.example.Online_Course_Management_Work.dto.AuthResponseDTO;
import com.example.Online_Course_Management_Work.dto.LoginRequestDTO;
import com.example.Online_Course_Management_Work.dto.RegisterRequestDTO;
import com.example.Online_Course_Management_Work.dto.UserResponseDTO;
import com.example.Online_Course_Management_Work.entity.Role;
import com.example.Online_Course_Management_Work.entity.User;
import com.example.Online_Course_Management_Work.exception.InvalidRequestException;
import com.example.Online_Course_Management_Work.mapper.UserMapper;
import com.example.Online_Course_Management_Work.repository.UserRepository;
import com.example.Online_Course_Management_Work.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(RegisterRequestDTO request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new InvalidRequestException("Email already registered");
        });

        Role role = request.getRole() == null ? Role.STUDENT : request.getRole();

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .profilePicture(request.getProfilePicture())
                .build();

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidRequestException("Invalid email or password");
        }

        return AuthResponseDTO.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .profilePicture(user.getProfilePicture())
                .message("Login successful")
                .build();
    }
}

