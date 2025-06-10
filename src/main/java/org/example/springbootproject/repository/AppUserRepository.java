package org.example.springbootproject.repository;

import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    UserDTO getUserById(Long id);
    Optional<AppUser> getUserByUsername(String username);
}
