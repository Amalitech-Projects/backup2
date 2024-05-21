package com.example.CartService;

import com.example.CartService.Repositories.CarRepository;
import com.example.CartService.Repositories.CardRepository;
import com.example.CartService.Repositories.CartRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    public final CartRepository cartRepository;

    public final CardRepository cardRepository;
    public final CarRepository carRepository;

    public List<Cart> getCart(){
        return cartRepository.findAll();
    }

    public List<Cart> getUserCart(String userId){
        return cartRepository.findByUserId(userId);
    }

    public Cart addToCart(Cart cart){
        return cartRepository.insert(cart);
    }

    public Cars addCar(Cars car){
        return carRepository.insert(car);
    }

    public Cart updateCart( String id, Cart newCartDetails){
        return cartRepository.insert(newCartDetails);
    }


    // Card Service
    public Card addCardDetails(Card cardDetails){
        return cardRepository.insert(Card
                .builder()
                        .userId(cardDetails.getUserId())
                        .cardNumber(cardDetails.getCardNumber())
                        .cardHolderName(cardDetails.getCardHolderName())
                        .cvv(cardDetails.getCvv())
                        .expirationDate(cardDetails.getExpirationDate())
                .build());
    }

    public Card  getACard(String id){
        return cardRepository.findByUserId(id).orElseThrow(() -> new NotFoundException("Card not found"));
    }

    public List<Cars> getCars(){
        return carRepository.findAll();
    }


    public void deleteCardDetails(String id) {
        cartRepository.deleteById(id);
    }
}