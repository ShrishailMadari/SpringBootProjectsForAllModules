package com.shyloostyle.security.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JWTUtils {

    @Value("${spring.app.jwtSecrete}")
    private String jwtSecrete;

    @Value("${spring.app.jwtExpirationMs}")
    private String jwtExpirationMs;

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);


    public String getJWTFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization Header: {}",bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7); // removing the prefix
        }
        return null;
    }

    private SecretKey getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode();
//        return Keys.hmacShaKeyFor(keyBytes);
        return null;
    }

    public String generateTokenFromUserName(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername()) // Replaces setSubject()
                .issuedAt(new Date()) // Replaces setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Replaces setExpiration()
                .signWith(getSigningKey()) // Uses the secure key
                .compact();
    }
}
