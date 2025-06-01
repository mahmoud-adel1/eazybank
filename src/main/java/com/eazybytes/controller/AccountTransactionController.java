package com.eazybytes.controller;

import com.eazybytes.dto.response.AccountTransactionResponseDto;
import com.eazybytes.service.AccountTransactionService;
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
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;

    @GetMapping("/myAccountTransactions")
    public ResponseEntity<List<AccountTransactionResponseDto>> getBalanceDetails(Authentication authentication) {
        List<AccountTransactionResponseDto> accountTransactionResponseDtoList =
                accountTransactionService.getAccountTransactionDetails(authentication);

        if (accountTransactionResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountTransactionResponseDtoList);

    }
}
