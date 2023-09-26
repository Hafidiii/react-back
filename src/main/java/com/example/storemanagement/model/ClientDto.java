package com.example.storemanagement.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String status;
    private Set<RoleDto> roleDto;
}
