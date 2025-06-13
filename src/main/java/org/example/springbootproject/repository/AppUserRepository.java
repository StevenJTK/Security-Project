package org.example.springbootproject.repository;

import org.example.springbootproject.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Interface allowing access to data
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    /**
     *  Fetches unique id
     * @param id id of the user
     * @return the user of the specific id
     */
    AppUser getUserById(Long id);

    /**
     *  Fetches user by their username
     * @param username name of the user
     * @return optional that checks if AppUser is found or empty
     */

    Optional<AppUser> getUserByUsername(String username);
}
