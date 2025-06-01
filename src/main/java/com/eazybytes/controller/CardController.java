package com.eazybytes.controller;

import com.eazybytes.dto.response.CardResponseDto;
import com.eazybytes.service.CardService;
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
public class CardController {

    private final CardService cardService;

    @GetMapping("/myCards")
    public ResponseEntity<List<CardResponseDto>> getCardDetails(Authentication authentication) {

        List<CardResponseDto> cardResponseDtoList = cardService.getCardDetails(authentication.getName());
        if (cardResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(cardResponseDtoList);

    }


}
