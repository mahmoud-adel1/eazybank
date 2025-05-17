package com.eazybytes.mapper;

import com.eazybytes.dto.response.LoanResponseDto;
import com.eazybytes.model.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanMapper {
    public static LoanResponseDto mapToResponseDto(Loan loan) {
        LoanResponseDto loanResponseDto = new LoanResponseDto();
        loanResponseDto.setLoanNumber(loan.getLoanNumber());
        loanResponseDto.setStartDt(loan.getStartDt());
        loanResponseDto.setLoanType(loan.getLoanType());
        loanResponseDto.setTotalLoan(loan.getTotalLoan());
        loanResponseDto.setAmountPaid(loan.getAmountPaid());
        loanResponseDto.setOutstandingAmount(loan.getOutstandingAmount());
        return loanResponseDto;
    }

    public static List<LoanResponseDto> mapToResponseDtoList(List<Loan> loans) {
        List<LoanResponseDto> loanResponseDtoList = new ArrayList<>();
        for (Loan loan : loans) {
            loanResponseDtoList.add(mapToResponseDto(loan));
        }

        return loanResponseDtoList;
    }
}
