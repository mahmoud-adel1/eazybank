package com.eazybytes.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AccountRequestDto {

    private String accountType;
    private String branchAddress;

}
