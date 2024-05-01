package com.example.UserService;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserCustomResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

}
