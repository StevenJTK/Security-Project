package org.example.springbootproject.controller;


import org.example.springbootproject.config.SecurityConfig;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    SecurityConfig securityConfig;

    public AdminController(UserService userService, SecurityConfig securityConfig) {
        this.userService = userService;
        this.securityConfig = securityConfig;
    }

    @GetMapping
    public ResponseEntity <String> verifyAdminControl(@RequestParam String username, @RequestParam String password) {
        AppUser au = userService.verifyLoginCredentials(username, password);

        if(au == null) {
            return ResponseEntity.notFound().build();
        } else {
            if(au.getRole().equals("admin")) {
                return ResponseEntity.ok("Welcome Admin");
            }
        }
            return ResponseEntity.ok("You do not have access");
    }
}
