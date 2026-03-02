package com.example.LMS.Controller;

import com.example.LMS.Model.User;
import com.example.LMS.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.AuthProvider;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        AuthProvider service;
        if (userService.login(email, password)) {
            return "dashboard";
        }

        model.addAttribute("error", "Invalid Credentials");
        return "login";
    }
}
