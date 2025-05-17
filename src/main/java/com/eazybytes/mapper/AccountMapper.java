package com.eazybytes.mapper;

import com.eazybytes.dto.response.AccountResponseDto;
import com.eazybytes.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

    public static AccountResponseDto mapToResponseDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccountNumber(account.getNumber());
        accountResponseDto.setAccountType(account.getAccountType());
        accountResponseDto.setBranchAddress(account.getBranchAddress());
        return accountResponseDto;
    }

    public static List<AccountResponseDto> mapToResponseDtoList(List<Account> accounts) {
        List<AccountResponseDto> accountResponseDtoList = new ArrayList<>();
        for (Account account : accounts) {
            accountResponseDtoList.add(mapToResponseDto(account));
        }
        return accountResponseDtoList;
    }


}
