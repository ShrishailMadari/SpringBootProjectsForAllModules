package com.shyloostyle.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MethodSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain customSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for testing (enable it in production)
                .authorizeHttpRequests(req -> req.anyRequest().authenticated()) // Allow method-level security
                .httpBasic(withDefaults()); // Enable basic authentication
        return httpSecurity.build();
    }


    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails user1 = User.builder()
                .username("User1")
                .password(passwordEncoder.encode("password1"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("password2"))
                .roles("ADMIN")
                .build();
        UserDetails manager = User.builder()
                .username("manager1")
                .password(passwordEncoder.encode("password3"))
                .roles("MANAGER")
                .build();
        UserDetails superAdmin = User.builder()
                .username("superAdmin")
                .password(passwordEncoder.encode("password3"))
                .roles("SUPERADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,admin,manager,superAdmin);
    }



}
