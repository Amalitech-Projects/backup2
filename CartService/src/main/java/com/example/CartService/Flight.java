package com.example.CartService;

import com.mongodb.DBObject;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class Flight {

        private String Id;
        private String cartId;
        private DBObject flight;

}
