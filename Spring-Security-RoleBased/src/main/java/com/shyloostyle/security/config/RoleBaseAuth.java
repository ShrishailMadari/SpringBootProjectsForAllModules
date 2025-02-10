package com.shyloostyle.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
public class RoleBaseAuth {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails user1 = User.builder()
                .username("User1")
                .password(passwordEncoder.encode("Password1"))
                .roles("User")
                .build();

        UserDetails admin1 = User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("Password2"))
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(user1,admin1);
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers("/api/Admin").hasRole("Admin")
                        .requestMatchers("/api/User").hasRole("User")
                        .requestMatchers("/api/RoleBased").hasAnyRole("User", "Admin")
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return httpSecurity.build();
    };
}
