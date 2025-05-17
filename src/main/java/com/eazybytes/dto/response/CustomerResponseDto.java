package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomerResponseDto {

    private Long customerId;

    private String name;

    private String email;

    private Set<RoleResponseDto> roles;

}
