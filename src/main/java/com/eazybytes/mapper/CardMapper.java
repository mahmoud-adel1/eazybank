package com.eazybytes.mapper;

import com.eazybytes.dto.response.CardResponseDto;
import com.eazybytes.model.Card;

import java.util.ArrayList;
import java.util.List;


public class CardMapper {
    public static CardResponseDto mapToResponseDto(Card card) {
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setCardId(card.getCardId());
        cardResponseDto.setCardNumber(card.getCardNumber());
        cardResponseDto.setCardType(card.getCardType());
        cardResponseDto.setTotalLimit(card.getTotalLimit());
        cardResponseDto.setAmountUsed(card.getAmountUsed());
        cardResponseDto.setAvailableAmount(card.getAvailableAmount());
        return cardResponseDto;
    }

    public static List<CardResponseDto> mapToResponseDtoList(List<Card> cards) {
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for (Card card : cards) {
            cardResponseDtoList.add(mapToResponseDto(card));
        }
        return cardResponseDtoList;
    }
}
