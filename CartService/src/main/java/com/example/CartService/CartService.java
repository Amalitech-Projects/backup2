package com.example.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    public final CartRepository cartRepository;

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

}