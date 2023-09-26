package com.example.storemanagement.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {

    private Long id;
    private String emplacement;
    private LocalDateTime updatedDate;
    private int quantity;

}
