package org.example.springbootproject.service;

import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    /**
     *  Checks for a username and provides user details for authentication
     *
     * @param username name of the user to be fetched
     * @return returns the users password and role
     * @throws UsernameNotFoundException if the username cannot be found
     */

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetches username and throws not found if user does not match
        AppUser user = appUserRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Returns user details if name matches
        return new User(user.getUsername(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }

}
