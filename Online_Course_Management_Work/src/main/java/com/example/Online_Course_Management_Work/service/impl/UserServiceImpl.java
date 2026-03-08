package com.example.Online_Course_Management_Work.service.impl;

import com.example.Online_Course_Management_Work.dto.UserResponseDTO;
import com.example.Online_Course_Management_Work.exception.ResourceNotFoundException;
import com.example.Online_Course_Management_Work.mapper.UserMapper;
import com.example.Online_Course_Management_Work.repository.UserRepository;
import com.example.Online_Course_Management_Work.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Cacheable(value = "users", key = "#id")
    public UserResponseDTO getById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}

