package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private Long totalLimit;

    @Column(name = "amount_used")
    private Long amountUsed;

    @Column(name = "available_amount")
    private Long availableAmount;

    @Column(name = "create_dt")
    private LocalDate createDt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
