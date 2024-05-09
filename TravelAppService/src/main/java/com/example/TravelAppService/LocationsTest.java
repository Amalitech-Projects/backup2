package com.example.TravelAppService;

import com.amadeus.resources.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LocationsTest {
    private String type;
    private String subType;
    private String name;
    private String detailedName;
    private String timeZoneOffset;
    private String iataCode;
    private GeoCode geoCode;
    private Location.Address address;
    private Object distance;
    private Analytics analytics;
    private Object relevance;

    // Constructors, getters, and setters

    // Inner classes for nested structures



    // Constructors, getters, and setters
}
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    class GeoCode {
        private double latitude;
        private double longitude;

        // Constructors, getters, and setters
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
     class Address {
            private String cityName;
            private String cityCode;
            private String countryName;
            private String countryCode;
            private String regionCode;

        // Constructors, getters, and setters
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
   class Analytics {
        private Object flights;
        private Travelers travelers;

        // Constructors, getters, and setters

    }
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        class Travelers {
            private Double score;

        }
