package com.example.storemanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class Client {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String username;
   private String firstName;
   private String lastName;
   private String phone;
   private String eamil;
   private String password;
   private String status;

   @ManyToMany(fetch = FetchType.EAGER)
   private Set<Roles> roles;


   @OneToMany(mappedBy = "client")
   private Set<Command> command = new HashSet<>();

}
