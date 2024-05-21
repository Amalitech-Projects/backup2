package com.example.CartService;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
public class Price {
    private String currency;
    private String baseCurrency;
    private BigDecimal basePrice;
    private BigDecimal driveAwayPrice;
    private BigDecimal discount;
    private boolean quoteAllowed;
    private DisplayPricing displayPricing;

    // Getters and setters
}
