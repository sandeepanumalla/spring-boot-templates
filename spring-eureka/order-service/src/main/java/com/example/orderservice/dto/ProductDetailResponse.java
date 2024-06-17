package com.example.orderservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {
    public long productId;
    public String productName;
    public String productDescription;
}
