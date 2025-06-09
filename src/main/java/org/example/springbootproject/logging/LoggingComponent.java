 package org.example.springbootproject.logging;

import org.example.springbootproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingComponent {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void logRegistration(String username) {
        logger.info("User registered: {}", username);
    }

    public void logDeletion(String username) {
        logger.info("User deleted: {}", username);
    }
}
