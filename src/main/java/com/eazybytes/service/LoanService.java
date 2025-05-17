package com.eazybytes.service;

import com.eazybytes.dto.response.CustomerResponseDto;
import com.eazybytes.dto.response.LoanResponseDto;
import com.eazybytes.exceptions.CustomerNotFoundException;
import com.eazybytes.mapper.CustomerMapper;
import com.eazybytes.mapper.LoanMapper;
import com.eazybytes.model.Loan;
import com.eazybytes.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final CustomerService customerService;

    public List<LoanResponseDto> getLoanDetails(String email) {
        if (!customerService.customerExistsByEmail(email)) {
            throw new CustomerNotFoundException("Customer not found!");
        }

        CustomerResponseDto customerResponseDto = CustomerMapper.mapToResponseDto(customerService.getCustomerByEmail(email));

        List<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customerResponseDto.getCustomerId());
        if (loans != null) {
            List<LoanResponseDto> loanResponseDtoList = LoanMapper.mapToResponseDtoList(loans);
            for (LoanResponseDto loanResponseDto : loanResponseDtoList) {
                loanResponseDto.setCustomerId(customerResponseDto.getCustomerId());
            }
            return loanResponseDtoList;
        } else {
            return Collections.emptyList();
        }
    }
}
