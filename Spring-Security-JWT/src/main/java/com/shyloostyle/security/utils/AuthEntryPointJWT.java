package com.shyloostyle.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
//custom handling for authentication related error
@Component
public class AuthEntryPointJWT implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJWT.class);
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        logger.error("UnAuthorized error: {} ",authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

//        now we hv to give the proper error response
        final Map<String,Object> errorResponseBody = new HashMap<>();
        errorResponseBody.put("Status",HttpServletResponse.SC_UNAUTHORIZED);
        errorResponseBody.put("error","UnAuthorized");
        errorResponseBody.put("message",authException.getMessage());
        errorResponseBody.put("path",request.getContextPath());
        // the path
        // where user was hitting the request

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),errorResponseBody);
    }
}
