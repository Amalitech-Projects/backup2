package com.example.CartService;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Hotel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class Cart {

    @Id
    private String Id;
    private String userId;
    private FlightOfferSearch[] flight;
    private Hotel[] stay;
    private String paid;

}


