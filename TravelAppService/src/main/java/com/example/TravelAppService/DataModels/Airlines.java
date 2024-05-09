package com.example.TravelAppService.DataModels;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Airlines {

    private String type;
    private String iataCode;
    private String icaoCode;
    private String businessName;
    private String commonName;

}
