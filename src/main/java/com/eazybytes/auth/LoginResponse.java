package com.eazybytes.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private Long customerId;
    private String token;
}
