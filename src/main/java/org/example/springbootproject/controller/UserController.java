package org.example.springbootproject.controller;


import org.example.springbootproject.config.SecurityConfig;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    SecurityConfig securityConfig;

    //konstruktor
    public UserController(UserService userService, SecurityConfig securityConfig) {
        this.userService = userService;
        this.securityConfig = securityConfig;
    }


    //loggar in och verifierar användaren
    @GetMapping
    public ResponseEntity <String> verifyAdminControl(@RequestParam String username, @RequestParam String password) {
        AppUser au = userService.verifyLoginCredentials(username, password);

        if(au == null) {
            return ResponseEntity.notFound().build();
        } else {
            //både user och admin har tillgång till sidan
            if(au.getRole().equals("user") || au.getRole().equals("admin")) {
                return ResponseEntity.ok("Welcome User");
            }
        }
        return ResponseEntity.ok("You do not have access");
    }
}
