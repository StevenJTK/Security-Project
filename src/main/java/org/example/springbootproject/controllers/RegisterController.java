package org.example.springbootproject.controllers;

import jakarta.validation.Valid;
import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity < String > submitForm(@Valid @RequestBody UserDTO user, BindingResult result) {
        // Här kan du spara användaren eller validera

        // System.out.println("Registrerad: " + user.getUsername());
        // Implement proper console message
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().toString());
        }
        userService.saveUser(user);
        return ResponseEntity.ok("Success");
    }
}
