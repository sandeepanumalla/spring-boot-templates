package com.example.orderservice.service;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    public Order getOrderById(Integer id);
    public OrderResponse order(List<OrderRequest> itemsToOrder) throws Exception;
    public void cancelOrder(List<Integer> itemsToCancel);
}
