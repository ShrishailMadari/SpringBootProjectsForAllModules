package com.shyloostyle.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> authorize.
                        requestMatchers("/", "/login").permitAll().anyRequest()
                        .authenticated())
                .oauth2Login(request -> request.loginPage("/login")
                        .defaultSuccessUrl("/home", true));
        return httpSecurity.build();
    }
}
