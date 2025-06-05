package org.example.springbootproject.service;

import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.model.AppUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void saveUser(UserDTO dto) {
        AppUser appUser = toAppUser(dto);
        System.out.println("console.log");
    }

    private AppUser toAppUser(UserDTO dto) {
        AppUser au = new AppUser();
        au.setUsername(dto.getUsername());
        au.setPassword(dto.getPassword());
        au.setRole(dto.getRole());

        return au;
    }

}
