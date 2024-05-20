package com.example.CartService;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private String id;
    private String name;
    private Size size;
    private String imageLink;
    private String propositionType;
    private String carType;
    private String transmission;
    private String fuelType;
    private boolean airConditioner;
    private boolean airBag;
    private boolean freeCancellation;
    private boolean unlimitedMileage;
    private String sippCode;
    private String fuelPolicy;
    private String insurancePackage;
    private String specialOfferText;
    private Price price;
    private Fees fees;
    private String purchaseLink;
    private Location pickUpLocation;
    private Location dropOffLocation;
    private Supplier supplier;

    // Getters and setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Size {
    private String[] doorCounts;
    private String seatCount;
    private String bigSuitcaseCount;
    private String smallSuitcaseCount;
    private String suitcaseCount;

    // Getters and setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class DisplayPricing {
    private BigDecimal totalPrice;
    private BigDecimal pricePerDay;
    private BigDecimal payNowPrice;
    private BigDecimal payAtPickupPrice;

    // Getters and setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Fees {

    private String fee;
    // Define properties if any
    // Getters and setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Location {
    private String id;
    private String code;
    private String name;
    private boolean onAirport;
    private double longitude;
    private double latitude;

    // Getters and setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Supplier {
    private String name;
    private String smallLogo;
    private String address;
    private String dropOffAddress;
    private boolean hasShuttle;
    private double longitude;
    private double latitude;
    private SupplierRating tags;

    // Getters and setters
}

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
 class SupplierRating {
    private double averageRating;
    private double valueForMoney;
    private double efficiency;
    private double collectTime;
    private double dropOffTime;
    private double cleanliness;
    private double condition;
    private double locating;

    // Getters and setters
}
