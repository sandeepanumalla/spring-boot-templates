package com.example.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDetailsResponse {
    public int productId;
    public String productName;
    public String productDescription;
}
