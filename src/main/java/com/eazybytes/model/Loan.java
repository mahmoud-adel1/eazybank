package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Long loanNumber;

    @Column(name = "start_dt")
    private LocalDate startDt;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private Long totalLoan;

    @Column(name = "amount_paid")
    private Long amountPaid;

    @Column(name = "outstanding_amount")
    private Long outstandingAmount;

    @Column(name = "create_dt")
    private LocalDate createDt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
