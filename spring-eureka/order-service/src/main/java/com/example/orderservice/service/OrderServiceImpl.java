package com.example.orderservice.service;

import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.ProductDetail;
import com.example.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final WebClient webClient;
    private final ObjectMapper jacksonObjectMapper;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Override
    public Order getOrderById(Integer id) {
        return null;
    }

    @Override
    public OrderResponse order(List<OrderRequest> itemsToOrder) throws Exception {

        // iterate each item from the arguments
        // make a stock availability check to inventory service.
        // if any stock is not available then return a status code
        // else send a stock reduce request to the inventory service.
        // the inventory service will respond in json string format
        // we need to convert it into InventoryResponse using objectMapper
        // this InventoryResponse has product ID and their details and need to be stored into orders table
        // then this InventoryResponse should be returned
        List<ProductDetail> productDetailList = new ArrayList<>();
        for(OrderRequest orderRequest : itemsToOrder) {
            String availabilityCheckResponse = availabilityCheck(orderRequest);
            InventoryResponse inventoryResponse = null;
            try {
                if(!availabilityCheckResponse.isEmpty() && availabilityCheckResponse.equals("OUT_OF_STOCK")) {
                    throw new Exception(orderRequest.getProductId() + "is OUT_OF_STOCK");
                } else {
                    inventoryResponse = callInventoryServiceToReduceStock(orderRequest);
//                    inventoryResponse = jacksonObjectMapper.readValue(reduceStockResponse, InventoryResponse.class);
                    ProductDetail productDetail = modelMapper.map(inventoryResponse.getProductDetails(), ProductDetail.class);
                    productDetailList.add(productDetail);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return storeOrderDetails(productDetailList);

    }

    private String availabilityCheck(OrderRequest orderRequest) {
        // Implement this method to call the inventory service and return the response as a JSON string

//        return webClient
//                .get()
//                .uri(uriBuilder ->
//                        uriBuilder
//                                .path("http://inventory-service/inventory/{productId}/availability")
//                                        .queryParam("quantity", orderRequest.getQuantity())
//                                                .build(orderRequest.getProductId()))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();

        return restTemplate.getForEntity("http://inventory-service/inventory/{productId}/availability?quantity={quantity}",
                String.class, orderRequest.getProductId(), orderRequest.getQuantity()).getBody();
    }

    private InventoryResponse callInventoryServiceToReduceStock(OrderRequest orderRequest) {
        // Implement this method to call the inventory service to reduce the stock
//        return webClient.put().uri(uriBuilder -> uriBuilder
//                .path("http://inventory-service/inventory/{productId}/reduce")
//                .queryParam("quantity", orderRequest.getQuantity())
//                .build(orderRequest.getProductId()))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();

        String url = "http://inventory-service/inventory/" + orderRequest.getProductId() + "/reduce?quantity=" + orderRequest.getQuantity();

        ResponseEntity<InventoryResponse> response =  restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                InventoryResponse.class);
        return response.getBody();
    }

    private OrderResponse storeOrderDetails(List<ProductDetail> productDetailsList) {
        // Implement this method to store the order details into the orders table
        Order order = new Order();
        order.setProductDetails(productDetailsList);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @Override
    public void cancelOrder(List<Integer> itemsToCancel) {

    }
}
