package com.example.storemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String eamil;
    private String password;
}
