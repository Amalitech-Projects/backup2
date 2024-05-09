package com.example.TravelAppService.TravelServices;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Hotel;
import com.example.TravelAppService.DataModels.HotelOffers;
import com.example.TravelAppService.RequestParam.HotelParamByGeoCode;
import com.example.TravelAppService.RequestParam.HotelParamsByCity;
import com.example.TravelAppService.TravelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelOffersService {


    public final TravelService travelService;

    // Search for Hotels according to Geo Location
    public List<HotelOffers> hotels(HotelParamByGeoCode hotelParams) throws JsonProcessingException, ResponseException {

        Hotel[] hotelOfferSearches = travelService.amadeus.referenceData.locations.hotels.byGeocode.get(
                Params.with("latitude", hotelParams.getLatitude())
                        .and("longitude", hotelParams.getLongitude())
                        .and("ratings", hotelParams.getRatings())
                        .and("checkInDate", hotelParams.getCheckInDate())
                        .and("checkOutDate", hotelParams.getCheckOutDate())
        );


        if (hotelOfferSearches[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + hotelOfferSearches[0].getResponse().getStatusCode());
        }

        return getHotelOffers(hotelOfferSearches);
    }


    // Search for Hotels according to CityCode
    public List<HotelOffers> hotelsByCity(HotelParamsByCity hotelParams) throws JsonProcessingException, ResponseException {

        Hotel[] hotelOfferSearches = travelService.amadeus.referenceData.locations.hotels.byCity.get(
                Params.with("cityCode", hotelParams.getCityCode())
                        .and("ratings", hotelParams.getRatings())
                        .and("checkInDate", hotelParams.getCheckInDate())
                        .and("checkOutDate", hotelParams.getCheckOutDate())
        );


        if (hotelOfferSearches[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + hotelOfferSearches[0].getResponse().getStatusCode());
        }

        return getHotelOffers(hotelOfferSearches);
    }

    private List<HotelOffers> getHotelOffers(Hotel[] hotels) throws JsonProcessingException {
        List<HotelOffers> flightsData = new ArrayList<>();

        for(Hotel hotel: hotels){
            flightsData.add(getHotelOffers(hotel));
        }
        return flightsData;
    }

    private HotelOffers getHotelOffers(Hotel hotel) throws JsonProcessingException {
        return HotelOffers
                .builder()
                .subtype(hotel.getSubtype())
                .uicCode(hotel.getUicCode())
                .name(hotel.getName())
                .hotelId(hotel.getHotelId())
                .address(hotel.getAddress())
                .geoCode(hotel.getGeoCode())
                .googlePlaceId(hotel.getGooglePlaceId())
                .distance(hotel.getDistance())
                .iataCode(hotel.getIataCode())
                .openjetAirportId(hotel.getOpenjetAirportId())
                .build();
    }
}
