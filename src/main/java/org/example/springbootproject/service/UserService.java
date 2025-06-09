package org.example.springbootproject.service;

import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.logging.LoggingComponent;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    private final AppUserRepository appUserRepository;
    private final LoggingComponent loggingComponent;

    public UserService(AppUserRepository appUserRepository, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.loggingComponent = loggingComponent;
    }

    public void saveUser(UserDTO dto) {
        AppUser appUser = toAppUser(dto);
        System.out.println("console.log");
        appUserRepository.save(appUser);
        loggingComponent.logRegistration(appUser.getUsername());
    }

    public boolean removeUser(Long id) {
        if (appUserRepository.existsById(id)) {
            loggingComponent.logDeletion(appUserRepository.getUserById(id).getUsername());
            appUserRepository.deleteById(id);
            return true;
        }
        return false;
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
