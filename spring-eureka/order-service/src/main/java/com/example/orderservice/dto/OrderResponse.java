package com.example.orderservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponse {
    private Long orderId;
    private List<ProductDetailsResponse> productDetails;
}
