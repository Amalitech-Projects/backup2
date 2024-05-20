package com.example.TravelAppService.TravelServices;

import com.example.TravelAppService.RequestParam.PaymentBody;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final WebClient webClient;

    private final String secretKey = "sk_test_c6dc8df7551b3851030c53c823dda902cd35fbbf";

    public PaymentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.paystack.co")
                .defaultHeader("Authorization", "Bearer " + secretKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public Mono<String> initializeTransaction(PaymentBody paymentBody) {
        return webClient.post()
                .uri("/transaction/initialize")
                .body(BodyInserters.fromValue(paymentBody))
                .retrieve()
                .bodyToMono(String.class);
    }

}
