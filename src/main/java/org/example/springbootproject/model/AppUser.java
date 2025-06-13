package org.example.springbootproject.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Contains primary attributes for a user
 * and getters and setters
 */
@Entity
public class AppUser {

    private String username;
    private String password;
    private String role;
    private boolean consentGiven;

    // Empty constructor
    public AppUser() {}

    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Get username
    public String getUsername() { return username; }

    // Set username
    public void setUsername(String username) { this.username = username; }

    // Get password
    public String getPassword() { return password; }

    // Set password
    public void setPassword(String password) { this.password = password; }

    // Get id
    public Long getId() {
        return id;
    }

    // Set id
    public void setId(Long id) {
        this.id = id;
    }

    // Get role
    public String getRole() {
        return role;
    }

    // Set role
    public void setRole(String role) {
        this.role = role;
    }

    // Returns true to consent
    public boolean isConsentGiven() {
        return consentGiven;
    }

    // Sets consent
    public void setConsentGiven(boolean consentGiven) {
        this.consentGiven = consentGiven;
    }
}
