package com.example.TravelAppService.RequestParam;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Data
public class HotelParamsByCity {

    private String geoId;
    private String checkIn;
    private String checkOut;
    private int pageNumber;
    private String currencyCode;

}
