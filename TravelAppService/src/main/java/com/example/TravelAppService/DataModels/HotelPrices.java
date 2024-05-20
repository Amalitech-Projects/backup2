package com.example.TravelAppService.DataModels;

import com.amadeus.resources.HotelOfferSearch;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class HotelPrices {
    private String type;
    private HotelOfferSearch.Hotel hotel;
    private boolean available;
    private HotelOfferSearch.Offer[] offers;
    private String self;
}
