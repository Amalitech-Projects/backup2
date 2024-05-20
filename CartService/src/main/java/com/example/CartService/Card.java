package com.example.CartService;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
@Builder
public class Card {

        @Id
        private String id;
        private String userId;
        private String cardNumber;
        private String cardHolderName;
        private String expirationDate;
        private String cvv;

}
