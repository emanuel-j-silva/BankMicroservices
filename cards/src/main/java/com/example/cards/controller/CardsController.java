package com.example.cards.controller;

import com.example.cards.dto.CardsDTO;
import com.example.cards.dto.ResponseDTO;
import com.example.cards.service.ICardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.cards.constants.CardsConstants.*;


@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {

    private ICardsService iCardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@Valid @RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(STATUS_201, MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDTO> fetchCardDetails(@RequestParam
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {

        CardsDTO cardsDTO = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCardDetails(@Valid @RequestBody CardsDTO cardsDTO) {

        boolean isUpdated = iCardsService.updateCard(cardsDTO);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(STATUS_200,MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(STATUS_417,MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCardDetails(@RequestParam
                                                             @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                             String mobileNumber) {

        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(STATUS_200,MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(STATUS_417,MESSAGE_417_DELETE));
        }
    }
}
