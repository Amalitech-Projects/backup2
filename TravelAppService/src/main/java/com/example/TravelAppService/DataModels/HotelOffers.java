package com.example.TravelAppService.DataModels;

import com.amadeus.resources.Hotel;
import com.amadeus.resources.HotelOfferSearch;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class HotelOffers {

    private String subtype;
    private String name;
    private String timeZoneName;
    private String iataCode;
    private Hotel.Address address;
    private Hotel.GeoCode geoCode;
    private String googlePlaceId;
    private String openjetAirportId;
    private String uicCode;
    private String hotelId;
    private String chainCode;
    private Hotel.Distance distance;
    private String lastUpdate;
}


