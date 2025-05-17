package com.eazybytes.mapper;

import com.eazybytes.dto.request.ContactRequestDto;
import com.eazybytes.dto.response.ContactResponseDto;
import com.eazybytes.model.Contact;

import java.time.LocalDate;

public class ContactMapper {

    public static Contact mapToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        contact.setContactName(contactRequestDto.getContactName());
        contact.setContactEmail(contactRequestDto.getContactEmail());
        contact.setSubject(contactRequestDto.getSubject());
        contact.setMessage(contactRequestDto.getMessage());
        contact.setCreateDt(LocalDate.now());
        return contact;
    }

    public static ContactResponseDto mapToDto(Contact contact) {
        ContactResponseDto contactResponseDto = new ContactResponseDto();
        contactResponseDto.setContactId(contact.getContactId());
        contactResponseDto.setContactName(contact.getContactName());
        contactResponseDto.setContactEmail(contact.getContactEmail());
        contactResponseDto.setSubject(contact.getSubject());
        contactResponseDto.setMessage(contact.getMessage());
        return contactResponseDto;
    }


}
