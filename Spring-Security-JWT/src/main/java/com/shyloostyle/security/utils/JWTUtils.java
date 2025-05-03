package com.shyloostyle.security.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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
//Helper Class for JWT:
@Component
public class JWTUtils {

    //need to be fetched from prop file
    @Value("${spring.app.jwtSecrete}")
    private String jwtSecrete; //secrete for signing the tokens

    @Value("${spring.app.jwtExpirationMs}")
    private String jwtExpirationMs; //in millisecond

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

// to extract jwt header from the servlet request
    public String getJWTFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization Header: {}",bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            //checking the bearerToken with null and
            // whether starts with bearer or not
            return bearerToken.substring(7); // removing the prefix starting from 0 index
        }
        return null;
    }

    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode();
//        return Keys.hmacShaKeyFor(keyBytes);
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecrete));
        }

    public String generateTokenFromUserName(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername()) // Replaces setSubject()
                .issuedAt(new Date()) // Replaces setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Replaces setExpiration()
                .signWith(getSigningKey()) // Uses the secure key
                .compact();
    }
    public String getUserNameFromToken(String token){
        return Jwts.parser().verifyWith((SecretKey) getSigningKey()) //verifying with key
                .build() // building it
                .parseEncryptedClaims(token)
                .getPayload().getSubject(); // get the subject from the builded payload
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) getSigningKey())
                    .build()
                    .parseEncryptedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Malformed JWT: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Invalid signature: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Token is null or empty: " + e.getMessage());
        }
        return false;
    }
}
