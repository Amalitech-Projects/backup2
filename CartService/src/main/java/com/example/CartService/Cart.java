package com.example.CartService;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Hotel;
import com.mongodb.DBObject;
import lombok.*;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Update;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class Cart {

    @Id
    public String id;
    private String userId;
    private DBObject flight;
    private DBObject stay;
    private boolean paid;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

}

