package com.example.orderservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
public class ProductDetail {

    @Column(name = "product_id", nullable = false)
    private int productId;
    private String productName;
    private int quantity;

    public ProductDetail() {
    }

}
