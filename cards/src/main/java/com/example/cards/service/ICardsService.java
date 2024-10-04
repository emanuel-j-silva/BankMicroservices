package com.example.cards.service;

import com.example.cards.dto.CardsDTO;

public interface ICardsService {

    void createCard(String mobileNumber);
    CardsDTO fetchCard(String mobileNumber);
    boolean updateCard(CardsDTO cardsDTO);
    boolean deleteCard(String mobileNumber);

}
