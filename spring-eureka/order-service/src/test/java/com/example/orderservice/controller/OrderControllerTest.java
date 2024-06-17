package com.example.orderservice.controller;

import com.example.orderservice.OrderServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

//@SpringBootTest(classes = OrderServiceApplication.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WebClient webClient = new WebClient.Builder();


    @Test
    public void contextLoads() {
    }

    @Test
    public void orderAnItemTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/order/1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.orderId").value( 1))
                .andExpect(jsonPath("$.productDetails.productName").value("apple"))
                .andExpect(jsonPath("$.productDetails.quantity").value(2));
    }


    @Test
    public void testMicroservice() throws Exception {
        DiscoveryClient discoveryClient = mock(DiscoveryClient.class);
        when(discoveryClient.getInstances("inventory-service"))
                .thenReturn(Collections.singletonList(new ServiceInstance() {
                    @Override
                    public String getServiceId() {
                        return "inventory-service";
                    }

                    @Override
                    public String getHost() {
                        return "localhost";
                    }

                    @Override
                    public int getPort() {
                        return 8081; // Assuming inventory-service is running on port 8081
                    }

                    @Override
                    public boolean isSecure() {
                        return false;
                    }

                    @Override
                    public URI getUri() {
                        return null;
                    }



                    @Override
                    public Map<String, String> getMetadata() {
                        return Map.of();
                    }
                }));

        String inventoryResponse = WebClient.builder().build()
                .get()
                .uri("http://localhost:8081/inventory/2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(inventoryResponse);
    }




}
