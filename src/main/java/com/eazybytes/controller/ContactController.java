package com.eazybytes.controller;

import com.eazybytes.dto.request.ContactRequestDto;
import com.eazybytes.dto.response.ContactResponseDto;
import com.eazybytes.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<ContactResponseDto> savedContactInquiryDetails(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        return contactService.savedContactInquiryDetails(contactRequestDto);
    }

}
