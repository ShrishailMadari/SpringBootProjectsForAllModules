package com.shyloostyle.security.controller;

import com.shyloostyle.security.utils.JWTUtils;
import com.shyloostyle.security.utils.LoginRequest;
import com.shyloostyle.security.utils.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class JwtController {

    AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user(){
        return "Hello,User!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/Admin")
    public String admin(){
        return "Hello,Admin!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> AuthenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUserName()
            ,loginRequest.getPassword()));
        }catch (AuthenticationException exception){
            Map<String,Object> response= new HashMap<>();
            response.put("message","Bad credentials");
            response.put("status",false);
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateTokenFromUserName(userDetails);
        //if no roles then we can remove this
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        LoginResponse loginResponse = new LoginResponse(jwtToken, userDetails.getUsername(), roles);
        return ResponseEntity.ok(loginResponse);
    }
}
