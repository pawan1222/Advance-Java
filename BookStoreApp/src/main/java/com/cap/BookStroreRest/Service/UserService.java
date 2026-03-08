package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.*;
import com.cap.BookStroreRest.Entity.User;
import com.cap.BookStroreRest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDTO registerUser(RegisterUserDTO dto){

        // DTO → Entity
        User user = modelMapper.map(dto, User.class);

        // Save in database
        User savedUser = userRepository.save(user);

        // Entity → DTO
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    public UserResponseDTO loginUser(LoginUserDTO dto){

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO updateUser(Long id, UpdateUserDTO dto){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    public void deleteUser(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    public UserResponseDTO getUserById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    public List<UserResponseDTO> getAllUsers(){

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }
}