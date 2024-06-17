package com.example.inventoryservice.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {
    public long id;
    public ProductDetailsResponseDTO productDetails;
    public int quantity;
    public String status;
}
