package com.shyloostyle.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(){
        System.out.println("Authenticating User");
        return new ResponseEntity<>("User Authenticated", HttpStatus.OK);
    }
}
