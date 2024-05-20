package com.example.CartService.FlightResponse;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Flight {
        private String id;
        private String type;
        private String source;
        private boolean instantTicketingRequired;
        private boolean disablePricing;
        private boolean nonHomogeneous;
        private boolean oneWay;
        private boolean paymentCardRequired;
        private String lastTicketingDate;
        private int numberOfBookableSeats;
        private List<Itinerary> itineraries;
        private com.example.CartService.Price price;
        private PricingOptions pricingOptions;
        private List<String> validatingAirlineCodes;
        private List<TravelerPricing> travelerPricings;
        private String choiceProbability;
        private String fareRules;

        // Getters and Setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Itinerary {
        private String duration;
        private List<Segment> segments;

        // Getters and Setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Segment {
        private Departure departure;
        private Arrival arrival;
        private String carrierCode;
        private String number;
        private Aircraft aircraft;
        private Operating operating;
        private String duration;
        private String stops;
        private String id;
        private int numberOfStops;
        private boolean blacklistedInEU;
        private String co2Emissions;

}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Departure {
        private String iataCode;
        private String terminal;
        private String at;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Arrival {
        private String iataCode;
        private String terminal;
        private String at;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Aircraft {
        private String code;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Operating {
        private String carrierCode;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Price {
        private String currency;
        private String total;
        private String base;
        private List<Fee> fees;
        private String grandTotal;
        private String taxes;
        private String refundableTaxes;
        private String margin;
        private String billingCurrency;
        private String additionalServices;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Fee {
        private String amount;
        private String type;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class PricingOptions {
        private boolean includedCheckedBagsOnly;
        private List<String> fareType;
        private String corporateCodes;
        private boolean refundableFare;
        private boolean noRestrictionFare;
        private boolean noPenaltyFare;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class TravelerPricing {
        private String travelerId;
        private String fareOption;
        private String travelerType;
        private String associatedAdultId;
        private com.example.CartService.Price price;
        private List<FareDetailsBySegment> fareDetailsBySegment;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class FareDetailsBySegment {
        private String segmentId;
        private String cabin;
        private String fareBasis;
        private String brandedFare;
        private String segmentClass;
        private String allotmentDetails;
        private String sliceDiceIndicator;
        private IncludedCheckedBags includedCheckedBags;
        private List<Amenity> amenities;
        private boolean allotment;

}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class IncludedCheckedBags {
        private int quantity;
        private int weight;
        private String weightUnit;
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Amenity {
        private String code;
        private String description;
        private boolean isChargeable;
        private String amenityType;
}