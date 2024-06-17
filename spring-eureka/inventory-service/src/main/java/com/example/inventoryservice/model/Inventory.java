package com.example.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.REMOVE , orphanRemoval = true)
    private ProductDetails productDetails;

    private int quantity;
    private String status;
}
