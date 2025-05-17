package com.eazybytes.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {

    @NotBlank(message = "Name is mandatory")
    private String contactName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String contactEmail;

    @NotBlank(message = "Subject is mandatory")
    private String subject;

    @NotBlank(message = "Message is mandatory")
    private String message;

}
