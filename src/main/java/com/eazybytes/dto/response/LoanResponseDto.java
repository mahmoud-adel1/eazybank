package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LoanResponseDto {

    private Long customerId;

    private Long loanNumber;

    private LocalDate startDt;

    private String loanType;

    private Long totalLoan;

    private Long amountPaid;

    private Long outstandingAmount;

}
