package com.example.CartService;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    public final CartRepository cartRepository;

    public final CardRepository cardRepository;

    public List<Cart> getCart(){
        return cartRepository.findAll();
    }

    public List<Cart> getUserCart(String userId){
        return cartRepository.findByUserId(userId);
    }

    public Cart addToCart(Cart cart){
        return cartRepository.insert(cart);
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


    public void deleteCardDetails(String id) {
        cartRepository.deleteById(id);
    }
}