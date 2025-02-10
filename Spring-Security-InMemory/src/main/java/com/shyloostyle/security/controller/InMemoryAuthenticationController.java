package com.shyloostyle.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InMemoryAuthenticationController {

    @GetMapping("/Authenticate")
    public ResponseEntity<String> inMemory(){
        System.out.println("In Memory Authentication");
        return new ResponseEntity<>("In Memory Authentication", HttpStatus.OK);
    }
}
