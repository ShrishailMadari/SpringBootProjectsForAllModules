package com.shyloostyle.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoleBasedAuthController {

    @GetMapping("/RoleBased")
    public ResponseEntity<String> roleBasedAuth(){
        System.out.println("Role based Authentication");
        return new ResponseEntity<>("Role Based Authentication", HttpStatus.OK);
    }

    @GetMapping("/User")
    public ResponseEntity<String> userAuth(){
        System.out.println("User based Authentication");
        return new ResponseEntity<>("User: Role Based Authentication", HttpStatus.OK);
    }

    @GetMapping("/Admin")
    public ResponseEntity<String> adminAuth(){
        System.out.println("Admin Role based Authentication");
        return new ResponseEntity<>("Admin Role Based Authentication", HttpStatus.OK);
    }
}
