package com.shyloostyle.security.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //This annotation helps the spring to maintain its life cycle
//OncePerRequestFilter : this class intercepts the request only one per request
public class AuthenticateTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateTokenFilter.class);

//    public AuthenticateTokenFilter(JWTUtils jwtUtils, UserDetailsService userDetailsService) {
//        this.jwtUtils = jwtUtils;
//        this.userDetailsService = userDetailsService;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
        String jwtToken = parsToken(request);
        // now start the Valid Token
        if(jwtToken != null && jwtUtils.validateToken(jwtToken)){
            String userNameFromToken = jwtUtils.getUserNameFromToken(jwtToken);
            // now hv to load the user details using username
            UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//           enhancing the token with additional details getting from the request
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//          effectively authenticating user for the duration
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }}catch (Exception e){
            logger.error("Cannot set user Authentication: {}",e);
        }
        filterChain.doFilter(request,response);

    }


    // it will get token from the header by removing the bearer
    private String parsToken(HttpServletRequest request) {
        String jwt = jwtUtils.getJWTFromHeader(request);
        logger.debug("AuthenticateTokenFilter.class :{}",jwt);
        return jwt;
    }
}
