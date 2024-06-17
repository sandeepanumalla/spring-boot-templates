package com.example.orderservice;

import com.example.orderservice.dto.InventoryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceApplicationTests {

    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    void testObjectMapper() throws JsonProcessingException {

        String response = "{\"id\":28,\"productDetails\":{\"productId\":52,\"productName\":\"apple\",\"productDescription\":\"royal gala apple\"},\"quantity\":3,\"status\":\"AVAILABLE\"}";
        InventoryResponse inventoryResponse = jacksonObjectMapper.readValue(response, InventoryResponse.class);
        System.out.println(inventoryResponse.getProductDetails().getProductName());
    }

}
