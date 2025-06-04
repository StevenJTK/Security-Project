package org.example.springbootproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() //  tillåter allt
                )
                .csrf(csrf -> csrf.disable()) //  stänger av CSRF (behövs ofta för Swagger POST)
                .formLogin(form -> form.disable())  // Stäng av form login (ingen HTML-login)
                .httpBasic(basic -> basic.disable()); // Stäng av HTTP Basic auth

        return http.build();
    }
}
