package org.example.springbootproject.service;

import org.example.springbootproject.config.SecurityConfig;
import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.logging.LoggingComponent;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;


/**
 * Service class handling actions such as saving, removing & verifying users
 */

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

    /**
     * Method for adding a user into the database
     *
     * @param dto transfers data object that contains the information
     */

    public void saveUser(UserDTO dto) {

        //  Calls appUser class to fetch details for creating a user & logs it
        AppUser appUser = toAppUser(dto);
        System.out.println("console.log");

        // Saves the user to the repository
        appUserRepository.save(appUser);

        // Logs the registration
        loggingComponent.logRegistration(appUser.getUsername());
    }

    /**
     *  Method to remove a user from the database
     *
     * @param id is fetched from the repository, verified and removes the user if true
     * @return if true, the user is deleted from the repository, if false, the user does not exist or if id did not match
     */

    public boolean removeUser(Long id) {
        // Checks if the id matches the user
        if (appUserRepository.existsById(id)) {
            // Logs the deletion
            loggingComponent.logDeletion(appUserRepository.getUserById(id).getUsername());
            // Deletes the user
            appUserRepository.deleteById(id);
            return true;
        }
        // False if user id does not match or does not exist
        return false;
    }

    /**
     * Maps AppUser by converting it via UserDTO
     *
     * @param dto calls dto to fetch user details and sets the attributes via calling AppUser
     * @return returns AppUser object
     */

    private AppUser toAppUser(UserDTO dto) {
        AppUser au = new AppUser();
        au.setUsername(dto.getUsername());
        au.setPassword(dto.getPassword());
        au.setRole(dto.getRole());
        au.setConsentGiven(dto.isConsentGiven());

        return au;
    }


    /**
     * Verifies if the users details are correct
     * @param username requires a username for login
     * @param password requires a password for login
     * @return returns user if successful, returns null if details mismatch
     */

    public AppUser verifyLoginCredentials(String username, String password) {
       // Calls the repository for a specific username
        Optional<AppUser> au =  appUserRepository.getUserByUsername(username);

       // Fetches the password
       if(au.isPresent()) {
            System.out.println(password);
            System.out.println(au.get().getPassword());

            // Checks if hashed password matches
            if(securityConfig.passwordEncoder().matches(password, au.get().getPassword())) {

                // Returns a found user on correct password
                System.out.println("User found");
                return au.get();
            }
        }
        // Returns user not found if user does not exist or wrong password
        System.out.println("User not found");
        return null;
    }

}
