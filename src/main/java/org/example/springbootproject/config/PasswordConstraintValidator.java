package org.example.springbootproject.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        if (password.replaceAll("[^0-9]", "").length() < 2) {
            return false;
        }

        if (password.replaceAll("[a-zA-Z0-9]", "").length() < 2) {
            return false;
        }
        return true;
    }
}
