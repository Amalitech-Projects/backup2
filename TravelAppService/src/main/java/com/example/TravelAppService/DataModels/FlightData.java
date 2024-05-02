package com.example.TravelAppService.DataModels;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightData{
    private String id;
    private String type;
    private String source;
    private boolean instantTicketingRequired;
    private boolean disablePricing;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private boolean paymentCardRequired;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
    private FlightOfferSearch.Itinerary[] itineraries;
    private FlightOfferSearch.SearchPrice price;
    private FlightOfferSearch.PricingOptions pricingOptions;
    private String[] validatingAirlineCodes;
    private FlightOfferSearch.TravelerPricing[] travelerPricings;
    private String choiceProbability;
    private FlightOfferSearch.FareRules fareRules;

}

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class FlightPricing{
    private String type;
    private FlightOfferSearch[] flightOffers;
    private FlightPrice.BookingRequirements bookingRequirements;
}