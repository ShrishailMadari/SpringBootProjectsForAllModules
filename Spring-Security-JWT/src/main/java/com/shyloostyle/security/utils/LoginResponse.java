package com.shyloostyle.security.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor

public class LoginResponse {
    private String jwtToken;
    private String userName;
    private List<String > roles;
}
