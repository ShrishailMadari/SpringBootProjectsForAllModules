package com.shyloostyle.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MethodBasedController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> userAccess(){
        return new ResponseEntity<>("User Access Granted", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminAccess(){
        return new ResponseEntity<>("Admin Access Granted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/common")
    public ResponseEntity<String> commonAccess() {
        return new ResponseEntity<>("Common Access for Admin & User", HttpStatus.OK);
    }

    @Secured("ROLE_SUPERADMIN")
    @GetMapping("/superAdmin")
    public ResponseEntity<String> superAdminAccess() {
        return new ResponseEntity<>("SuperAdmin Access Granted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/admin-manager")
    public ResponseEntity<String> adminAndManagerAccess() {
        return new ResponseEntity<>("Admin & Manager Access Granted", HttpStatus.OK);
    }
}


    /*
    * 🔹 Final Summary
✅ Fix role names (Spring Security expects uppercase, hasRole('USER'))
✅ Ensure @Secured("ROLE_SUPERADMIN") uses the full ROLE_ prefix
✅ Keep SecurityFilterChain to enforce authentication
✅ Use @EnableMethodSecurity to activate method-based security
*
* */

