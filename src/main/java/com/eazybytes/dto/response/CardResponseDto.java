package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponseDto {

    private Long customerId;

    private Long cardId;

    private String cardNumber;

    private String cardType;

    private Long totalLimit;

    private Long amountUsed;

    private Long availableAmount;

}
