package com.eazybytes.auth;

import com.eazybytes.model.Customer;
import com.eazybytes.security.CustomUserDetails;
import com.eazybytes.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerService customerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest registerRequest) {

        Customer customer = customerService.registerCustomer(registerRequest);
        CustomUserDetails customUserDetails = new CustomUserDetails(customer);
        String jwtToken = jwtService.generateToken(customUserDetails);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setToken(jwtToken);
        return registerResponse;
    }


    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Customer customer = customerService.getCustomerByEmail(loginRequest.getEmail());
        CustomUserDetails customUserDetails = new CustomUserDetails(customer);
        String jwtToken = jwtService.generateToken(customUserDetails);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setCustomerId(customUserDetails.getCustomer().getId());
        loginResponse.setToken(jwtToken);
        return loginResponse;
    }
}
