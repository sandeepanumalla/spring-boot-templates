package com.example.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_details")
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @OneToOne(mappedBy = "productDetails")
    private Inventory inventory;
    private String productName;
    private String productDescription;
}
