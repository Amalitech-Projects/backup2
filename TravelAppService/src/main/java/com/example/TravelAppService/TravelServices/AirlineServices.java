package com.example.TravelAppService.TravelServices;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Airline;
import com.amadeus.resources.Hotel;
import com.example.TravelAppService.DataModels.Airlines;
import com.example.TravelAppService.DataModels.HotelOffers;
import com.example.TravelAppService.RequestParam.AirlineParam;
import com.example.TravelAppService.TravelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AirlineServices {

    public final TravelService travelService;

    public List<Airlines> getAirlineInfo(AirlineParam airlineCodes) throws ResponseException, JsonProcessingException {
        Airline[] airlines = travelService.amadeus.referenceData.airlines.get(Params
                .with("airlineCodes", airlineCodes.getAirlineCodes()));

        if (airlines.length > 0 && airlines[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + airlines[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        return getHotelOffers(airlines);
    }

    private List<Airlines> getHotelOffers(Airline[] airlines) throws JsonProcessingException {
        List<Airlines> flightsData = new ArrayList<>();

        for(Airline airline: airlines){
            flightsData.add(getHotelOffers(airline));
        }
        return flightsData;
    }

    private Airlines getHotelOffers(Airline airline) throws JsonProcessingException {
        return Airlines
                .builder()
                .type(airline.getType())
                .businessName(airline.getBusinessName())
                .commonName(airline.getCommonName())
                .icaoCode(airline.getIcaoCode())
                .iataCode(airline.getIataCode())
                .build();
    }

}
