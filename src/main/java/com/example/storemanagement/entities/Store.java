package com.example.storemanagement.entities;

import com.example.storemanagement.support.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Store extends BaseEntity {

    private Double quantity;
    private String emplacement;

    @OneToMany(mappedBy = "store")
    @ToString.Exclude
    private Set<Product> products;

}
