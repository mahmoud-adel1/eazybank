package com.eazybytes.controller;

import com.eazybytes.dto.response.AccountResponseDto;
import com.eazybytes.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAccountDetails(Authentication authentication) {

        List<AccountResponseDto> accountResponseDtoList = accountService.getAccountDetails(authentication.getName());

        if (accountResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountResponseDtoList);


    }
}
