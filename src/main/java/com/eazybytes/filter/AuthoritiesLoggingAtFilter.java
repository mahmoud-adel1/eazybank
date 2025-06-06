package com.eazybytes.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@Component
@Slf4j
public class AuthoritiesLoggingAtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Authentication validation in progress");
        chain.doFilter(request, response);
    }
}
