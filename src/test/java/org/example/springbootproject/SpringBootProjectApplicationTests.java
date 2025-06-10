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
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
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

    @Autowired
    private AppUserRepository userRepository;
    private MockMvc mockMvc;


    @Test // Passing test using H2
    @WithMockUser(username = "Steven", roles = {"ADMIN"})
    void testDeleteUserFromDatabase() throws Exception {
        appUserRepository.deleteById(34L);

    }

   /* Mock Experiment
    @Test
    @WithMockUser(username = "Steven", roles = {"ADMIN"})
    void testDeleteUserFromDatabaseVersionTwo() throws Exception {

        doNothing().when(appUserRepository).deleteById(34L);

        mockMvc.perform(delete("/users/{id}", 34L))
                .andExpect(status().isNoContent());
    } */
}
