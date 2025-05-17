package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {

    private Long customerId;

    private String customerName;

    private Long accountNumber;

    private String accountType;

    private String branchAddress;

}
