package org.example.springbootproject.service;

import org.example.springbootproject.config.SecurityConfig;
import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.logging.LoggingComponent;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {
    private final AppUserRepository appUserRepository;
    private final LoggingComponent loggingComponent;

    SecurityConfig securityConfig;

    public UserService(AppUserRepository appUserRepository, LoggingComponent loggingComponent, SecurityConfig securityConfig) {
        this.appUserRepository = appUserRepository;
        this.loggingComponent = loggingComponent;
        this.securityConfig = securityConfig;
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

    public AppUser verifyLoginCredentials(String username, String password) {
       Optional<AppUser> au =  appUserRepository.getUserByUsername(username);

        if(au.isPresent()) {
            System.out.println(password);
            System.out.println(au.get().getPassword());

            if(securityConfig.passwordEncoder().matches(password, au.get().getPassword())) {

            System.out.println("User found");
                return au.get();
            }
        }
        System.out.println("User not found");
        return null;
    }

}
