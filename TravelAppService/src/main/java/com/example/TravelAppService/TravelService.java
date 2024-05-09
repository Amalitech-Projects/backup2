package com.example.TravelAppService;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.example.TravelAppService.RequestParam.FlightInfoParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.bson.BsonSerializationException;
import org.bson.conversions.Bson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class TravelService {

    private static final String ClientId = "7Jj9arZWp6KP9H3Udbz6W2WsZNbCAG6A";
    private static final String ClientSecretKey = "AYFfyF8YTGXztvAt";

    private final ObjectMapper objectMapper;

    public final Amadeus amadeus = Amadeus.builder(ClientId, ClientSecretKey).build();



}
