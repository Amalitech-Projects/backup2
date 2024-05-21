package com.example.TravelAppService;

import com.amadeus.exceptions.ResponseException;
import com.example.TravelAppService.DataModels.*;
import com.example.TravelAppService.DataModels.MultipleFlightDto.FlightSearchRequest;
import com.example.TravelAppService.RequestParam.*;
import com.example.TravelAppService.TravelServices.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/request-service")
public class TravelController {

    public final TravelService travelService;

    // Flight Service
    public final FlightOffersService flightOffersService;
    public final Airports aiports;
    public final HotelsRapidApi hotelsRapidApi;
    public final PaymentService paymentService;
    public final AirlineServices airlineServices;

    @GetMapping("/")
    public ResponseEntity<List<LocationData>> getTest() throws ResponseException, JsonProcessingException {
        return ResponseEntity.ok(aiports.test());
    }

    @PostMapping("/airports")
    public ResponseEntity<List<LocationData>> getAirports(@RequestBody AirportParam keyword) throws ResponseException, JsonProcessingException {
        return ResponseEntity.ok(aiports.getAirports(keyword));
    }
//
//    @PostMapping("/airports")
//    public ResponseEntity<Mono<String>> getAirports(@RequestBody AirportParam query)  {
//        return ResponseEntity.ok(hotelsRapidApi.searchAirport(query));
//    }

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



    @PostMapping("/hotels-offers")
    public ResponseEntity<Mono<String>> getHotelsOffers(@RequestBody GeneralQueryParams hotelParams) {
        return ResponseEntity.ok(hotelsRapidApi.searchHotelsOffer(hotelParams));
    }

    @PostMapping("/hotel-offer")
    public ResponseEntity<Mono<String>> getHotelOffers(@RequestBody HotelParamsByCity hotelParams) {
        return ResponseEntity.ok(hotelsRapidApi.searchHotelOffer(hotelParams));
    }

    @PostMapping("/init-payment")
    public ResponseEntity<Mono<String>> initializePayment(@RequestBody PaymentBody paymentBody){
        return ResponseEntity.ok(paymentService.initializeTransaction(paymentBody));
    }

//    @PostMapping("/hotels-by-city")
//    public ResponseEntity<List<HotelOffers>> getHotelsByCity(@RequestBody HotelParamsByCity hotelParams) throws JsonProcessingException, ResponseException {
//        return ResponseEntity.ok(hotelOffersService.hotelsByCity(hotelParams));
//    }

}
