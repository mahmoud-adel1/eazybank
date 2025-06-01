package com.eazybytes.service;

import com.eazybytes.dto.request.AccountRequestDto;
import com.eazybytes.dto.response.AccountResponseDto;
import com.eazybytes.exceptions.AccountNotFoundException;
import com.eazybytes.mapper.AccountMapper;
import com.eazybytes.model.Account;
import com.eazybytes.model.Customer;
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

        Customer customer = customerService.getCustomerByEmail(email);
        List<Account> accounts = accountRepository.findByCustomerId(customer.getId());

        List<AccountResponseDto> accountResponseDtoList = AccountMapper.mapToResponseDtoList(accounts);

        for (AccountResponseDto accountResponseDto : accountResponseDtoList) {
            accountResponseDto.setCustomerId(customer.getId());
        }

        return accountResponseDtoList;
    }

    public AccountResponseDto getAccount(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(()->new AccountNotFoundException("Account not found!"));


        AccountResponseDto accountResponseDto = AccountMapper.mapToResponseDto(account);
        if (account.getCustomer() != null) {
            accountResponseDto.setCustomerId(account.getCustomer().getId());
        }
        return accountResponseDto;
    }

    public List<AccountResponseDto> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return AccountMapper.mapToResponseDtoList(accounts);
    }

    public AccountResponseDto updateAccount(Long accountId, AccountRequestDto accountRequestDto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()->new AccountNotFoundException("Account not found!"));

        account.setAccountType(accountRequestDto.getAccountType());
        account.setBranchAddress(accountRequestDto.getBranchAddress());

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToResponseDto(account);
    }


    public AccountResponseDto saveAccount(AccountRequestDto accountRequestDto, String email) {
        Account account = AccountMapper.mapToModel(accountRequestDto);
        Customer customer = customerService.getCustomerByEmail(email);
        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToResponseDto(savedAccount);
    }
}
