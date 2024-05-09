package com.example.TravelAppService.DataModels;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LocationData {
    private String type;
    private String subType;
    private String name;
    private String detailedName;
    private String timeZoneOffset;
    private String iataCode;
    private GeoCode geoCode;
    private Address address;
    private Double distance;
    private Object analytics;
    private Object relevance;

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class GeoCode {
        private double latitude;
        private double longitude;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Address {
        private String cityName;
        private String cityCode;
        private String countryName;
        private String countryCode;
        private String regionCode;
    }
}

