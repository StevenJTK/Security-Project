package org.example.springbootproject;

import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.repository.AppUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

@SpringBootTest
class SpringBootProjectApplicationTests {

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "Steven", roles = {"ADMIN"})
    void testSaveUser() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setUsername("Steven");
        appUser.setPassword("123");
        appUser.setRole("ADMIN");
        appUser.setConsentGiven(true);
        appUserRepository.save(appUser);
        Optional<AppUser> u = appUserRepository.getUserByUsername("Steven");

        assertEquals("Steven", u.get().getUsername());
        Assertions.assertNotEquals("222", u.get().getPassword());
        assertEquals("ADMIN", u.get().getRole());
        Assertions.assertTrue(u.get().isConsentGiven());
    }


}
