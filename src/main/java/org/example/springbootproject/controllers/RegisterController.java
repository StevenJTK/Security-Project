package org.example.springbootproject.controllers;

import org.example.springbootproject.model.AppUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @PostMapping
    public String submitForm(@RequestBody AppUser user) {
        // Här kan du spara användaren eller validera
       // System.out.println("Registrerad: " + user.getUsername());
        return "result"; // Visa en bekräftelsesida
    }
}
