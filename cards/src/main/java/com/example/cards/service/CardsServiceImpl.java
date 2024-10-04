package com.example.cards.service;

import com.example.cards.constants.CardsConstants;
import com.example.cards.dto.CardsDTO;
import com.example.cards.entity.Cards;
import com.example.cards.exception.CardAlreadyExistsException;
import com.example.cards.exception.ResourceNotFoundException;
import com.example.cards.mapper.CardsMapper;
import com.example.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService{

    private CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if (cards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber" + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDTO fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Card","mobileNumber",mobileNumber));

        return CardsMapper.mapToCardsDTO(cards,new CardsDTO());
    }

    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Cards cards = cardsRepository.findByMobileNumber(cardsDTO.getMobileNumber())
                .orElseThrow(()-> new ResourceNotFoundException("Card", "cardNumber", cardsDTO.getCardNumber()));

        CardsMapper.mapToCards(cardsDTO,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Card","mobileNumber",mobileNumber));

        cardsRepository.deleteById(cards.getCardId());
        return true;
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
}
