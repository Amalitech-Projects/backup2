package com.example.TravelAppService.RequestParam;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class HotelParamByGeoCode {
    private double longitude;
    private double latitude;
    private String keyword;
    private String checkInDate;
    private String checkOutDate;
    private int ratings;

}
