package com.example.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    public final CartService cartService;

    @GetMapping("/all")
    public List<Cart> getAllCarts(){
        return cartService.getCart();
    }
    @GetMapping("/user/{userId}")
    public List<Cart> getUserCart(@RequestParam String userId){
        return cartService.getUserCart(userId);
    }
    @PostMapping("/")
    public Cart addToCart(@RequestBody Cart cartDetails){
        return cartService.addToCart(cartDetails);
    }
    @PutMapping("/{id}")
    public Cart updateCart(@RequestParam String id, Cart cartDetails){
        return cartService.updateCart(id,cartDetails);
    }
    @DeleteMapping("/{id}")
    public Cart deleteCart(@RequestBody Cart cartDetails){
        return cartService.addToCart(cartDetails);
    }

}
