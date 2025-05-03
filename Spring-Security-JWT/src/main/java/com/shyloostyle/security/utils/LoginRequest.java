package com.shyloostyle.security.utils;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
