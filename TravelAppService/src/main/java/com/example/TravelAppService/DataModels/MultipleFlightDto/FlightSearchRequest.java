package com.example.TravelAppService.DataModels.MultipleFlightDto;

import lombok.Builder;

import java.util.List;

@Builder
public record FlightSearchRequest(
        String currencyCode,
        List<OriginDestination> originDestinations,
        List<Traveler> travelers,
        List<String> sources,
        SearchCriteria searchCriteria
) {
    public static record OriginDestination(
            String id,
            String originLocationCode,
            String destinationLocationCode,
            DepartureDateTimeRange departureDateTimeRange
    ) {
    }

    public static record DepartureDateTimeRange(
            String date,
            String time
    ) {
    }

    public static record Traveler(
            String id,
            String travelerType,
            List<String> fareOptions
    ) {
    }

    public static record SearchCriteria(
            int maxFlightOffers,
            FlightFilters flightFilters
    ) {
    }

    public static record FlightFilters(
            List<CabinRestriction> cabinRestrictions,
            CarrierRestrictions carrierRestrictions
    ) {
    }

    public static record CabinRestriction(
            String cabin,
            String coverage,
            List<String> originDestinationIds
    ) {
    }

    public static record CarrierRestrictions(
            List<String> excludedCarrierCodes
    ) {
    }
}
