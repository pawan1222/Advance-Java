package com.example.BookSecurity.Service;

import com.example.BookSecurity.Dto.AuthResponse;
import com.example.BookSecurity.Dto.LoginRequest;
import com.example.BookSecurity.Dto.RegisterRequest;
import com.example.BookSecurity.Entity.Role;
import com.example.BookSecurity.Entity.User;
import com.example.BookSecurity.Repository.UserRepository;
import com.example.BookSecurity.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public String register(RegisterRequest request){
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.from(request.getRole()));

        userRepository.save(user);

        return "User registered Successfully";
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new RuntimeException("User not Found"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generatetoken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}
