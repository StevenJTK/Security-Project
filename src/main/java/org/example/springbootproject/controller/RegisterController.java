package org.example.springbootproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.springbootproject.config.SecurityConfig;
import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    SecurityConfig securityConfig;

    private final UserService userService;

    public RegisterController(UserService userService, SecurityConfig securityConfig) {
        this.userService = userService;
        this.securityConfig = securityConfig;
    }

    @PostMapping
    public ResponseEntity < String > submitForm(@Valid @RequestBody UserDTO user, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().toString());
        }
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        return ResponseEntity.ok("Success");
    }
}
