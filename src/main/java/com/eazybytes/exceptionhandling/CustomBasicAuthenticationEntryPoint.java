package com.eazybytes.exceptionhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("eazybank-error-reason", "Authentication Failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        LocalDateTime localDateTime = LocalDateTime.now();
        String message = (authException != null && authException.getMessage() != null) ?
                authException.getMessage() : "Unauthorized";
        String path = request.getRequestURI();
        String jsonResponse =
                String.format("{\"timestamp\": \"%s\", \"status\": \"%d\", \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\",}",
                        localDateTime, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        message, path);
        response.getWriter().write(jsonResponse);
    }
}
