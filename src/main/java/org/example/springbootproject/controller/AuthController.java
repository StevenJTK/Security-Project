package org.example.springbootproject.controller;

import org.example.springbootproject.model.LoginRequest;
import org.example.springbootproject.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request-token")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    //konstruktor
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    //returnerar en token
    @PostMapping
    public ResponseEntity<String> token(@RequestBody LoginRequest loginRequest) {
        //autentisierar användaren
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
                String token = tokenService.generateToken(auth);
                return ResponseEntity.ok(token);
    }
}

