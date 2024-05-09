package com.example.TravelAppService;

import com.amadeus.exceptions.ResponseException;
import com.example.TravelAppService.DataModels.Airlines;
import com.example.TravelAppService.DataModels.FlightData;
import com.example.TravelAppService.DataModels.HotelOffers;
import com.example.TravelAppService.DataModels.LocationData;
import com.example.TravelAppService.DataModels.MultipleFlightDto.FlightSearchRequest;
import com.example.TravelAppService.RequestParam.*;
import com.example.TravelAppService.TravelServices.Airports;
import com.example.TravelAppService.TravelServices.AirlineServices;
import com.example.TravelAppService.TravelServices.FlightOffersService;
import com.example.TravelAppService.TravelServices.HotelOffersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request-service")
public class TravelController {

    public final TravelService travelService;

    // Flight Service
    public final FlightOffersService flightOffersService;
    public final HotelOffersService hotelOffersService;
    public final Airports aiports;
    public final AirlineServices airlineServices;

    @GetMapping("/")
    public ResponseEntity<List<LocationData>> getTest() throws ResponseException, JsonProcessingException {
        return ResponseEntity.ok(aiports.test());
    }

    @PostMapping("/airports")
    public ResponseEntity<List<LocationData>> getAirports(@RequestBody AirportParam keyword) throws ResponseException, JsonProcessingException {
        return ResponseEntity.ok(aiports.getAirports(keyword));
    }

    @PostMapping("/airlines")
    public ResponseEntity<List<Airlines>> getAirlines(@RequestBody AirlineParam airlineParam) throws ResponseException, JsonProcessingException {
        return ResponseEntity.ok(airlineServices.getAirlineInfo(airlineParam));
    }

    @PostMapping("/flights")
    public ResponseEntity<List<FlightData>> getFlights(@RequestBody FlightInfoParams flightRequest) throws ResponseException, JsonProcessingException {
        return ResponseEntity.ok(flightOffersService.flight(flightRequest));
    }

    @PostMapping("/multiple-flights")
    public ResponseEntity<List<FlightData>> getMultipleFlights(@RequestBody FlightSearchRequest flightRequest) throws ResponseException, JsonProcessingException {
        return ResponseEntity.ok(flightOffersService.multipleFlights(flightRequest));
    }

    @PostMapping("/hotels-by-geo-location")
    public ResponseEntity<List<HotelOffers>> getHotelsByGeoLocation(@RequestBody HotelParamByGeoCode hotelParams) throws JsonProcessingException, ResponseException {
        return ResponseEntity.ok(hotelOffersService.hotels(hotelParams));
    }

    @PostMapping("/hotels-by-city")
    public ResponseEntity<List<HotelOffers>> getHotelsByCity(@RequestBody HotelParamsByCity hotelParams) throws JsonProcessingException, ResponseException {
        return ResponseEntity.ok(hotelOffersService.hotelsByCity(hotelParams));
    }

}
