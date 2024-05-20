package com.example.TravelAppService.RequestParam;

public record PaymentBody (String email, String amount, String currency, String callback_url){
}
