package com.example.storemanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class Client {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String username;
   private String firstName;
   private String lastName;
   private String phone;
   private String email;
   private String password;
   private String status;

   @ManyToMany(fetch = FetchType.EAGER)
   private Set<Role> roles;

//   @OneToMany(mappedBy = "client")
//   private Set<Order> command = new HashSet<>();

}
