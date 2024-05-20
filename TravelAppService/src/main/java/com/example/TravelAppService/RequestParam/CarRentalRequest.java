package com.example.TravelAppService.RequestParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalRequest {

    private String pickUpPlaceId;
    private String pickUpLocationType;
    private String pickUpDate;
    private String dropOffDate;
    private String pickUpTime;
    private String dropOffTime;
    private String order;
    private String page;
    private String currencyCode;
}
