package com.Cbic_Aaklan_Project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity (not recommended for production)
                .authorizeRequests()
                .antMatchers("/api/**").authenticated() // Secure all /api endpoints
                .and()
                .httpBasic(); // Enable Basic Authentication

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        org.springframework.security.provisioning.InMemoryUserDetailsManager manager =
                new org.springframework.security.provisioning.InMemoryUserDetailsManager();

        manager.createUser(org.springframework.security.core.userdetails.User
                .withUsername("CBIC")
                .password(passwordEncoder.encode("Cbic@2024"))
                .roles("USER") // Assign roles as needed
                .build());

        return manager;
    }
}
