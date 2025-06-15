package org.example.springbootproject.controller;

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

    //konstruktor
    public RegisterController(UserService userService, SecurityConfig securityConfig) {
        this.userService = userService;
        this.securityConfig = securityConfig;
    }

    //sparar en användare i databasen
    @PostMapping
    public ResponseEntity < String > submitForm(@Valid @RequestBody UserDTO user, BindingResult result) {

        //om något av DTO-kraven INTE uppfylls
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().toString());
        }
        //om ok, hashar lösenordet
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        return ResponseEntity.ok("Success");
    }
}
