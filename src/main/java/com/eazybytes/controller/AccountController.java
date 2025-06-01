package com.eazybytes.controller;

import com.eazybytes.dto.request.AccountRequestDto;
import com.eazybytes.dto.response.AccountResponseDto;
import com.eazybytes.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/my-accounts")
    public ResponseEntity<List<AccountResponseDto>> getAccountDetails(Authentication authentication) {

        List<AccountResponseDto> accountResponseDtoList = accountService.getAccountDetails(authentication.getName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountResponseDtoList);


    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/account/{id}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable("id") Long accountId) {
        AccountResponseDto accountResponseDto = accountService.getAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAccounts() {
        List<AccountResponseDto> accountResponseDtoList = accountService.getAccounts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountResponseDtoList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/account/{id}")
    public ResponseEntity<AccountResponseDto> updateAccount(@PathVariable("id") Long accountId, @RequestBody AccountRequestDto accountRequestDto) {
        AccountResponseDto accountResponseDto = accountService.updateAccount(accountId, accountRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountResponseDto);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/account")
    public ResponseEntity<AccountResponseDto> saveAccount(@RequestBody AccountRequestDto accountRequestDto, Authentication authentication) {
        AccountResponseDto accountResponseDto = accountService.saveAccount(accountRequestDto, authentication.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountResponseDto);
    }





}
