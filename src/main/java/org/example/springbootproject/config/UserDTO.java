package org.example.springbootproject.config;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
    @NotBlank(message = "Name cannot be empty")
    private String username;

    @Pattern(regexp = "^(user|admin)$", message = "Role must be either 'user' or 'admin'")
    private String role;

    @Pattern(
            regexp = "^(?=(?:.*[A-Z]){1,})(?=(?:.*\\d){2,})(?=(?:.*[^A-Za-z0-9]){2,}).{8,}$",
            message = "Password must have 1 uppercase, 2 digits, and 2 special characters and must be 8 characters long"
    )
    private String password;

    @Schema(defaultValue = "false")
    @AssertTrue(message = "Consent given")
    private boolean consentGiven;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(boolean consentGiven) {
        this.consentGiven = consentGiven;
    }
}
