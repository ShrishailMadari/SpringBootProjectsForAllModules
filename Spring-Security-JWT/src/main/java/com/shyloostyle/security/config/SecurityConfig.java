package com.shyloostyle.security.config;

import com.shyloostyle.security.utils.AuthEntryPointJWT;
import com.shyloostyle.security.utils.AuthenticateTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig {
    @Autowired
    private  DataSource dataSource;
    @Autowired
    private  AuthEntryPointJWT unAuthorizeHandler;

    @Bean
    private AuthenticateTokenFilter authenticateTokenFilter(){
        return new AuthenticateTokenFilter();
    }
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(authorize->authorize
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/api/login").permitAll()
            .anyRequest().authenticated());
    httpSecurity.sessionManagement(session->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    httpSecurity.exceptionHandling(exception -> exception.authenticationEntryPoint(unAuthorizeHandler));
    httpSecurity.headers()


    }



}
