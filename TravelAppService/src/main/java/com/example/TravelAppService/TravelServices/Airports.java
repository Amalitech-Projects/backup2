package com.example.TravelAppService.TravelServices;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.Location;
import com.example.TravelAppService.DataModels.LocationData;
import com.example.TravelAppService.RequestParam.AirportParam;
import com.example.TravelAppService.TravelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class Airports {

    public final TravelService travelService;

    public List<LocationData> getAirports(AirportParam keyword) throws ResponseException, JsonProcessingException {
        Location[] locations = travelService.amadeus.referenceData.locations.get(
                Params
                .with("keyword", keyword.getQuery())
                .and("subType", Locations.CITY));

        if(locations[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + locations[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        return getFlightLocations(locations);
    }
    public List<LocationData> test() throws ResponseException, JsonProcessingException {
        Location[] locations = travelService.amadeus.referenceData.locations.get(
                Params
                .with("keyword", "LON")
                .and("subType", Locations.AIRPORT));

        if(locations[0].getResponse().getStatusCode() != 200) {
            System.out.println("Wrong status code: " + locations[0].getResponse().getStatusCode());
            System.exit(-1);
        }

        return getFlightLocations(locations);
    }

    private List<LocationData> getFlightLocations(Location[] flights) throws JsonProcessingException {
        List<LocationData> flightsData = new ArrayList<>();

        for(Location flight: flights){
            flightsData.add(getFlightLocations(flight));
        }
        return flightsData;
    }

    private LocationData getFlightLocations(Location location) throws JsonProcessingException {
        return LocationData
                .builder()
                .name(location.getName())
                .type(location.getType())
                .detailedName(location.getDetailedName())
                .analytics(location.getAnalytics())
                .subType(location.getSubType())
                .timeZoneOffset(location.getTimeZoneOffset())
                .geoCode(LocationData.GeoCode
                        .builder()
                        .latitude(location.getGeoCode().getLatitude())
                        .longitude(location.getGeoCode().getLongitude())
                        .build())
                .relevance(location.getRelevance())
                .address(LocationData.Address
                        .builder()
                        .cityCode(location.getAddress().getCityCode())
                        .cityName(location.getAddress().getCityName())
                        .countryCode(location.getAddress().getCountryCode())
                        .build())
                .build();
    }

}
