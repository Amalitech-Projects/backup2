package com.example.CartService;

//import com.example.CartService.Payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

//    public final PaymentService paymentService;

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
    public ResponseEntity<String> deleteCart(@PathVariable String id){
        cartService.deleteCardDetails(id);
        return ResponseEntity.ok("Cart Deleted Successfully");
    }

    // Card Details CRUD

    @PostMapping("/add-card")
    public Card addToCard(@RequestBody Card addNewCard){
        return cartService.addCardDetails(addNewCard);
    }

    @GetMapping("/card")
    public Card findCard(@RequestParam(name = "userId", required = true) String userId){
        return cartService.getACard(userId);
    }


}
