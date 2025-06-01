package com.eazybytes.mapper;

import com.eazybytes.dto.request.AccountRequestDto;
import com.eazybytes.dto.response.AccountResponseDto;
import com.eazybytes.model.Account;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

    public static AccountResponseDto mapToResponseDto(Account account) {

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        if (account.getCustomer() != null) {
            accountResponseDto.setCustomerId(account.getCustomer().getId());
        }
        accountResponseDto.setAccountNumber(account.getNumber());
        accountResponseDto.setAccountType(account.getAccountType());
        accountResponseDto.setBranchAddress(account.getBranchAddress());
        return accountResponseDto;
    }

    public static Account mapToModel(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setAccountType(accountRequestDto.getAccountType());
        account.setBranchAddress(accountRequestDto.getBranchAddress());
        account.setCreateDt(LocalDate.now());
        account.setAccountTransactions(null);
        return account;
    }

    public static List<AccountResponseDto> mapToResponseDtoList(List<Account> accounts) {
        List<AccountResponseDto> accountResponseDtoList = new ArrayList<>();
        for (Account account : accounts) {
            accountResponseDtoList.add(mapToResponseDto(account));
        }
        return accountResponseDtoList;
    }


}
