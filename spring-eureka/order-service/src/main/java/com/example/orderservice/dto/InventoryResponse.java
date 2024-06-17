package com.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    public long id;
    public ProductDetailsResponse productDetails;
    public int quantity;
    public String status;
}
