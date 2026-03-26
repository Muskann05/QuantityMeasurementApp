package com.spring.controller;

import com.spring.dto.LoginDTO;

import com.spring.dto.RegisterDTO;
import com.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return userService.login(dto);
    }
    @GetMapping("/check")
    public String open() {
        return "Working";
    }
}