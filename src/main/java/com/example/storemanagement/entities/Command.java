package com.example.storemanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String status;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client")
    private Client client;

    @ManyToMany(mappedBy = "command", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();


}
