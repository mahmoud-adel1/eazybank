package com.eazybytes.mapper;

import com.eazybytes.dto.response.AccountTransactionResponseDto;
import com.eazybytes.model.AccountTransaction;

import java.util.ArrayList;
import java.util.List;

public class AccountTransactionMapper {

    public static AccountTransactionResponseDto mapToResponseDto(AccountTransaction accountTransaction) {
        AccountTransactionResponseDto accountTransactionResponseDto = new AccountTransactionResponseDto();
        accountTransactionResponseDto.setTransactionId(accountTransaction.getTransactionId());
        accountTransactionResponseDto.setTransactionDt(accountTransaction.getTransactionDt());
        accountTransactionResponseDto.setTransactionSummary(accountTransaction.getTransactionSummary());
        accountTransactionResponseDto.setTransactionType(accountTransaction.getTransactionType());
        accountTransactionResponseDto.setTransactionAmt(accountTransaction.getTransactionAmt());
        accountTransactionResponseDto.setClosingBalance(accountTransaction.getClosingBalance());
        return accountTransactionResponseDto;
    }

    public static List<AccountTransactionResponseDto> mapToResponseDtoList(List<AccountTransaction> accountTransactions) {
        List<AccountTransactionResponseDto> accountTransactionResponseDtoList = new ArrayList<>();
        for (AccountTransaction accountTransaction : accountTransactions) {
            accountTransactionResponseDtoList.add(mapToResponseDto(accountTransaction));
        }
        return accountTransactionResponseDtoList;
    }
}
