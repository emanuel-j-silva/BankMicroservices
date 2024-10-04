package com.example.cards.mapper;

import com.example.cards.dto.CardsDTO;
import com.example.cards.entity.Cards;

public class CardsMapper {
    public static CardsDTO mapToCardsDTO(Cards cards, CardsDTO cardsDTO){
        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setMobileNumber(cards.getMobileNumber());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        cardsDTO.setAmountUsed(cards.getAmountUsed());
        cardsDTO.setAvailableAmount(cards.getAvailableAmount());
        return cardsDTO;
    }

    public static Cards mapToCards(CardsDTO cardsDTO, Cards cards){
        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setMobileNumber(cardsDTO.getMobileNumber());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        cards.setAvailableAmount(cardsDTO.getAvailableAmount());
        return cards;
    }
}
