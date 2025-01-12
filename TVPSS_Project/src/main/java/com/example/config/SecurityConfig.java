package com.example.config;

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/login", "/register", "/css/**", "/img/**").permitAll() // Allow access to these paths
                .anyRequest().authenticated() // Protect all other paths
            .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .loginProcessingUrl("/perform_login") // Login processing URL
                .defaultSuccessUrl("/home", true) // Redirect after successful login
                .failureUrl("/login?error=true") // Redirect on login failure
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user@example.com")
            .password(passwordEncoder().encode("password"))
            .roles("USER");
    }
}