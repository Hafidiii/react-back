package com.example.storemanagement.entities;

import com.example.storemanagement.support.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product  extends BaseEntity {

    private String title;
    private double price;
    private String currency;
    private String company;
    private String info;
    private boolean inCart;
    private double count;
    private double total;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;



}