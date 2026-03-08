package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.LoginUserDTO;
import com.cap.BookStroreRest.DataTransferObject.RegisterUserDTO;
import com.cap.BookStroreRest.DataTransferObject.UpdateUserDTO;
import com.cap.BookStroreRest.DataTransferObject.UserResponseDTO;
import com.cap.BookStroreRest.Service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(
            @RequestBody RegisterUserDTO registerUserDTO){

        UserResponseDTO user = userService.registerUser(registerUserDTO);

        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(
            @RequestBody LoginUserDTO loginUserDTO){

        return ResponseEntity.ok(userService.loginUser(loginUserDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserDTO updateUserDTO){

        return ResponseEntity.ok(userService.updateUser(id, updateUserDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }
}