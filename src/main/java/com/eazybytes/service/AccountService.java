package com.eazybytes.service;

import com.eazybytes.dto.response.AccountResponseDto;
import com.eazybytes.dto.response.CustomerResponseDto;
import com.eazybytes.exceptions.CustomerNotFoundException;
import com.eazybytes.mapper.AccountMapper;
import com.eazybytes.mapper.CustomerMapper;
import com.eazybytes.model.Account;
import com.eazybytes.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final CustomerService customerService;
    private final AccountRepository accountRepository;

    public List<AccountResponseDto> getAccountDetails(String email) {

        if (!customerService.customerExistsByEmail(email)) {
            throw new CustomerNotFoundException("Customer not found!");
        }

        CustomerResponseDto customerResponseDto = CustomerMapper.mapToResponseDto(customerService.getCustomerByEmail(email));
        List<Account> accounts = accountRepository.findByCustomerId(customerResponseDto.getCustomerId());

        List<AccountResponseDto> accountResponseDtoList = AccountMapper.mapToResponseDtoList(accounts);

        for (AccountResponseDto accountResponseDto : accountResponseDtoList) {
            accountResponseDto.setCustomerId(customerResponseDto.getCustomerId());
        }

        return accountResponseDtoList;
    }

}
