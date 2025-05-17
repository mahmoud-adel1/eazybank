package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponseDto {

    private String contactId;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

}
