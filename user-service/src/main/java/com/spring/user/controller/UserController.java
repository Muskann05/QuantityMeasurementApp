package com.spring.user.controller;

import com.spring.user.dto.LoginDTO;
import com.spring.user.dto.RegisterDTO;
import com.spring.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "User APIs", description = "User registration and authentication")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER
    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return userService.register(dto);
    }

    // LOGIN (UPDATED 🔥)
    @Operation(summary = "Login user")
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginDTO dto) {
        String token = userService.login(dto);
        return Map.of("token", token);   // 🔥 IMPORTANT CHANGE
    }

    // TEST API
    @Operation(summary = "Check API status")
    @GetMapping("/check")
    public String open() {
        return "Working";
    }
}