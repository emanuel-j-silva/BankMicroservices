package com.example.cards.service;

import com.example.cards.constants.CardsConstants;
import com.example.cards.entity.Cards;
import com.example.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService{

    private CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {
        if (cardsRepository.findByMobileNumber(mobileNumber).isEmpty()){
            cardsRepository.save(createNewCard(mobileNumber));
        }
    }

    private Cards createNewCard(String mobileNumber){
        Cards card = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        card.setMobileNumber(mobileNumber);
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return card;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }
}
