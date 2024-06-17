package com.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    public int productId;
    public int quantity;
}
