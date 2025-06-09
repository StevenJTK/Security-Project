package org.example.springbootproject.repository;

import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    UserDTO getUserById(Long id);
}
