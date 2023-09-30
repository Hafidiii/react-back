package com.example.storemanagement.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandDto {

    private Long id;
    private String createdDate;
    private String updatedDate;
    private String status;
    private double total;
    private ClientDto clientDto;
    private List<ProductDto> productDto;
}
