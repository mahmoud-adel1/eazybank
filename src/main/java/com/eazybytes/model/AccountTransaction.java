package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "account_transactions")
@Getter
@Setter
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "transaction_dt")
    private LocalDate transactionDt;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_amt")
    private Long transactionAmt;

    @Column(name = "closing_balance")
    private Long closingBalance;

    @Column(name = "create_dt")
    private LocalDate createDt;

    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account account;

}
