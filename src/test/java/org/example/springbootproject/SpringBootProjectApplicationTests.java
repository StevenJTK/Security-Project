package org.example.springbootproject;

import org.example.springbootproject.config.SecurityConfig;
import org.example.springbootproject.config.UserDTO;
import org.example.springbootproject.model.AppUser;
import org.example.springbootproject.repository.AppUserRepository;
import org.example.springbootproject.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class SpringBootProjectApplicationTests {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
        AppUser au = new AppUser();
        au.setUsername("admin");
        au.setPassword(securityConfig.passwordEncoder().encode("admin"));
        au.setRole("admin");
        au.setConsentGiven(true);
        userRepository.save(au);
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "Steven", roles = {"admin"})
    void testSaveUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Steven");
        userDTO.setPassword("123NOraid4u()=+");
        userDTO.setRole("admin");
        userDTO.setConsentGiven(true);
        mockMvc.perform(post ("/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());
        Optional<AppUser> u = appUserRepository.getUserByUsername("Steven");

        assertEquals("Steven", u.get().getUsername());
        Assertions.assertNotEquals("222", u.get().getPassword());
        assertEquals("admin", u.get().getRole());
        assertTrue(u.get().isConsentGiven());
    }

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "Steven", roles = {"admin"})
    void testDeleteUserFromDatabase() throws Exception {
        AppUser appUser = appUserRepository.getUserByUsername("admin").get();
        assertTrue(appUserRepository.getUserByUsername("admin").isPresent());
        mockMvc.perform(delete("/remove/" + appUser.getId()));
        Assertions.assertFalse(appUserRepository.getUserByUsername("admin").isPresent());
    }

    @Test
  //  @WithMockUser(username = "Steven", roles = "admin")
    void testVerifyLoginCredentials() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Steven");
        userDTO.setPassword("123NOraid4u()=+");
        userDTO.setRole("admin");
        userDTO.setConsentGiven(true);
        mockMvc.perform(post ("/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());
        AppUser appUser = userService.verifyLoginCredentials("Steven", "123NOraid4u()=+");
        Assertions.assertNotNull(appUser);

    }
}
