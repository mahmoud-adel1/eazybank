package com.eazybytes.controller;

import com.eazybytes.dto.response.LoanResponseDto;
import com.eazybytes.service.LoanService;
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
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/loans")
    public ResponseEntity<List<LoanResponseDto>> getLoanDetails(Authentication authentication) {
        List<LoanResponseDto> loanResponseDtoList = loanService.getLoanDetails(authentication.getName());
        if (loanResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(loanResponseDtoList);
    }
}
