package com.example.orderservice.repository;

import com.example.orderservice.OrderServiceApplication;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.ProductDetail;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = OrderServiceApplication.class)
class OrderRepositoryTest {

    @Autowired
    public OrderRepository orderRepository;

    @Before("testGetOrderById")
    public void setup() {
        orderRepository.deleteAll();
    }


    @Test
    void testGetOrderById() {
        long orderId = 1;
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        assertNotNull(order);
        assertEquals(orderId, order.getOrderId());
    }

    @Test
    void testSaveOrders() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductName("Apple");
        productDetail.setQuantity(2);
        Order order = new Order();
        order.setProductDetails(List.of(productDetail));
        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder);
    }

    @Test
    void testRemoveOrder() {
        long orderId = 1;
        orderRepository.deleteById(orderId);
        Order order = orderRepository.findById(orderId).orElse(null);
        assertEquals(null, order);
    }
}
