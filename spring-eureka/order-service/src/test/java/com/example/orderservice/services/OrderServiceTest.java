package com.example.orderservice.services;

import com.example.orderservice.OrderServiceApplication;
import com.example.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = OrderServiceApplication.class)
public class OrderServiceTest {

    @MockBean
    private OrderService orderService;

    @Test
    public void testOrderService() {

    }

    @Test
    public void testGetOrderService() {

    }
}
