package com.example.inventoryservice.dto;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDetailsRequestDTO {
    private int quantity;
    private String productName;
    private String productDescription;
}
