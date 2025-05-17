package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountTransactionResponseDto {

    private Long customerId;

    private Long accountNumber;

    private String transactionId;

    private LocalDate transactionDt;

    private String transactionSummary;

    private String transactionType;

    private Long transactionAmt;

    private Long closingBalance;

}
