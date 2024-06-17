package com.example.inventoryservice.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class InventoryRequestDTO {
    private long productId;
    private int quantity;
    private String status;
}
