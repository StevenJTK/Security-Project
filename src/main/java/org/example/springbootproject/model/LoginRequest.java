package org.example.springbootproject.model;

/**
 *  Transfers data representing a user
 * @param username user's username
 * @param password user's password
 */

public record LoginRequest(String username, String password) {
}
