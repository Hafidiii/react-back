package com.example.storemanagement.model;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String company;
    private String currency;
    private double price;
    private List<CommandDto> commands;
}
