package com.eazybytes.service;

import com.eazybytes.dto.response.AccountResponseDto;
import com.eazybytes.dto.response.AccountTransactionResponseDto;
import com.eazybytes.dto.response.CustomerResponseDto;
import com.eazybytes.exceptions.CustomerNotFoundException;
import com.eazybytes.mapper.AccountTransactionMapper;
import com.eazybytes.mapper.CustomerMapper;
import com.eazybytes.model.AccountTransaction;
import com.eazybytes.repository.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepository;
    private final CustomerService customerService;
    private final AccountService accountService;

    public List<AccountTransactionResponseDto> getAccountTransactionDetails(Authentication authentication) {

        if (!customerService.customerExistsByEmail(authentication.getName())) {
            throw new CustomerNotFoundException("Customer not found!");
        }

        CustomerResponseDto customerResponseDto = CustomerMapper.mapToResponseDto(customerService.getCustomerByEmail(authentication.getName()));
        List<AccountResponseDto> accountResponseDtoList = accountService.getAccountDetails(authentication.getName());
        List<AccountTransactionResponseDto> accountTransactionResponseDtoList = new ArrayList<>();
        for (AccountResponseDto accountResponseDto : accountResponseDtoList) {
            List<AccountTransaction> transactions =  accountTransactionRepository.findByAccountNumber(accountResponseDto.getAccountNumber());
            List<AccountTransactionResponseDto> transactionResponseDtoList = AccountTransactionMapper.mapToResponseDtoList(transactions);
            for (AccountTransactionResponseDto accountTransactionResponseDto : transactionResponseDtoList) {
                accountTransactionResponseDto.setCustomerId(customerResponseDto.getCustomerId());
                accountTransactionResponseDto.setAccountNumber(accountResponseDto.getAccountNumber());
            }
            accountTransactionResponseDtoList.addAll(transactionResponseDtoList);
        }
        accountTransactionResponseDtoList.sort(Comparator.comparing(AccountTransactionResponseDto::getAccountNumber)
                .thenComparing(Comparator.comparing(AccountTransactionResponseDto::getTransactionDt).reversed()));
        return accountTransactionResponseDtoList;

    }
}
