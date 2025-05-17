package com.eazybytes.service;

import com.eazybytes.dto.request.ContactRequestDto;
import com.eazybytes.dto.response.ContactResponseDto;
import com.eazybytes.mapper.ContactMapper;
import com.eazybytes.model.Contact;
import com.eazybytes.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public ResponseEntity<ContactResponseDto> savedContactInquiryDetails(ContactRequestDto contactRequestDto) {
        Contact contact = ContactMapper.mapToEntity(contactRequestDto);
        contact.setContactId(getServiceRequestNumber());
        Contact savedContact = contactRepository.save(contact);
        ContactResponseDto contactResponseDto = ContactMapper.mapToDto(savedContact);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(contactResponseDto);

    }

    private static String getServiceRequestNumber() {
        Random random = new Random();
        int serviceRequestNumber = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + serviceRequestNumber;
    }
}
