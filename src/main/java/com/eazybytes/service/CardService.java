package com.eazybytes.service;

import com.eazybytes.dto.response.CardResponseDto;
import com.eazybytes.dto.response.CustomerResponseDto;
import com.eazybytes.exceptions.CustomerNotFoundException;
import com.eazybytes.mapper.CardMapper;
import com.eazybytes.mapper.CustomerMapper;
import com.eazybytes.model.Card;
import com.eazybytes.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CustomerService customerService;

    public List<CardResponseDto> getCardDetails(String email) {

        if (!customerService.customerExistsByEmail(email)) {
            throw new CustomerNotFoundException("Customer not found!");
        }

        CustomerResponseDto customerResponseDto = CustomerMapper.mapToResponseDto(customerService.getCustomerByEmail(email));
        List<Card> cards = cardRepository.findByCustomerId(customerResponseDto.getCustomerId());

        if (cards != null) {
            List<CardResponseDto> cardResponseDtoList = CardMapper.mapToResponseDtoList(cards);
            for (CardResponseDto cardResponseDto : cardResponseDtoList) {
                cardResponseDto.setCustomerId(customerResponseDto.getCustomerId());
            }
            return cardResponseDtoList;
        } else {
            return Collections.emptyList();
        }

    }
}
