package com.example.TravelAppService.TravelServices;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.TravelAppService.DataModels.FlightData;
import com.example.TravelAppService.DataModels.MultipleFlightDto.FlightSearchRequest;
import com.example.TravelAppService.RequestParam.FlightInfoParams;
import com.example.TravelAppService.TravelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightOffersService {

    private final TravelService travelService;

    private final ObjectMapper mapper;

    public List<FlightData> flight(FlightInfoParams flightRequest) throws JsonProcessingException, ResponseException {

        FlightOfferSearch[] flightoffersSearches = travelService.amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", flightRequest.getOriginLocationCode()).
                        and("destinationLocationCode", flightRequest.getDestinationLocationCode())
                        .and("departureDate", flightRequest.getDepartureDate())
                        .and("returnDate", flightRequest.getReturnDate())
                        .and("adults", flightRequest.getAdults())
                        .and("max", flightRequest.getMax()));

//        FlightPrice flightPricing = travelService.amadeus.shopping.flightOffersSearch.pricing.post(
//                flightoffersSearches[1],
//                Params.with("include", "detailed-fare-rules")
//                        .and("forceClass", "false")
//        );


        if (flightoffersSearches[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + flightoffersSearches[0].getResponse().getStatusCode());
            System.exit(-1);
            System.out.println(flightoffersSearches[0]);
        }

        return getFlightLocations(flightoffersSearches);
    }

    public List<FlightData> multipleFlights(FlightSearchRequest body) throws ResponseException, JsonProcessingException {
        String flightToString = mapper.writeValueAsString(body);
//        String body = "{\"currencyCode\":\"USD\",\"originDestinations\":[{\"id\":\"1\",\"originLocationCode\":\"RIO\",\"destinationLocationCode\":\"MAD\",\"departureDateTimeRange\":{\"date\":\"2024-08-01\",\"time\":\"10:00:00\"}},{\"id\":\"2\",\"originLocationCode\":\"MAD\",\"destinationLocationCode\":\"RIO\",\"departureDateTimeRange\":{\"date\":\"2024-08-05\",\"time\":\"17:00:00\"}}],\"travelers\":[{\"id\":\"1\",\"travelerType\":\"ADULT\",\"fareOptions\":[\"STANDARD\"]},{\"id\":\"2\",\"travelerType\":\"CHILD\",\"fareOptions\":[\"STANDARD\"]}],\"sources\":[\"GDS\"],\"searchCriteria\":{\"maxFlightOffers\":2,\"flightFilters\":{\"cabinRestrictions\":[{\"cabin\":\"ECONOMY\",\"coverage\":\"MOST_SEGMENTS\",\"originDestinationIds\":[\"1\"]}],\"carrierRestrictions\":{\"excludedCarrierCodes\":[\"AA\",\"TP\",\"AZ\"]}}}}";

        FlightOfferSearch[] flightOfferSearches = travelService.amadeus.shopping.flightOffersSearch.post(flightToString);
        return getFlightLocations(flightOfferSearches);
    }

    private List<FlightData> getFlightLocations(FlightOfferSearch[] flights) throws JsonProcessingException {
        List<FlightData> flightsData = new ArrayList<>();

        for(FlightOfferSearch flight: flights){
            flightsData.add(getFlightLocations(flight));
        }
        return flightsData;
    }

    private FlightData getFlightLocations(FlightOfferSearch flight) throws JsonProcessingException {
        return FlightData
                .builder()
                .oneWay(flight.isOneWay())
                .id(flight.getId())
                .price(flight.getPrice())
                .itineraries(flight.getItineraries())
                .instantTicketingRequired(flight.isInstantTicketingRequired())
                .travelerPricings(flight.getTravelerPricings())
                .fareRules(flight.getFareRules())
                .pricingOptions(flight.getPricingOptions())
                .type(flight.getType())
                .validatingAirlineCodes(flight.getValidatingAirlineCodes())
                .choiceProbability(flight.getChoiceProbability())
                .source(flight.getSource())
                .numberOfBookableSeats(flight.getNumberOfBookableSeats())
                .choiceProbability(flight.getChoiceProbability())
                .nonHomogeneous(flight.isNonHomogeneous())
                .build();
    }
}
