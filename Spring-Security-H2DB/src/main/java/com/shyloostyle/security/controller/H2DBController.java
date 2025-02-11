package com.shyloostyle.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class H2DBController {

    @GetMapping("/h2db")
    public ResponseEntity<String> validate(){
        System.out.println("H2 Db accessing");
        return new ResponseEntity<>("H2", HttpStatus.OK);
    }
}
