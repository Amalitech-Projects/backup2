package com.example.TravelAppService.RequestParam;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class HotelParamsByCity {

    private String cityCode;
    private String radiusUnit;
    private String checkInDate;
    private String checkOutDate;
    private String radius;
    private int ratings;

}
