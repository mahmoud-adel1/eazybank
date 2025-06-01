package com.eazybytes.controller;

import com.eazybytes.dto.response.CustomerResponseDto;
import com.eazybytes.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/my-customer")
    public ResponseEntity<CustomerResponseDto> getCustomerDetails(Authentication authentication) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerDetails(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerResponseDto);
    }



}
