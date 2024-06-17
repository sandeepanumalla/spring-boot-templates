package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    public Order() {
    }

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ElementCollection
    @CollectionTable(name = "order_product_details",
            joinColumns = @JoinColumn(name = "order_id"))
    private List<ProductDetail> productDetails;

}
