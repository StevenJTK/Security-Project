package org.example.springbootproject.service;

import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    private final AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void saveUser(UserDTO dto) {
        AppUser appUser = toAppUser(dto);
        System.out.println("console.log");
        appUserRepository.save(appUser);
    }

    private AppUser toAppUser(UserDTO dto) {
        AppUser au = new AppUser();
        au.setUsername(dto.getUsername());
        au.setPassword(dto.getPassword());
        au.setRole(dto.getRole());
        au.setConsentGiven(dto.isConsentGiven());

        return au;
    }

}
