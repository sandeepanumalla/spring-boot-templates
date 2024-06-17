package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponseDTO;
import com.example.inventoryservice.dto.ProductDetailsRequestDTO;
import com.example.inventoryservice.dto.ProductDetailsResponseDTO;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.ProductDetails;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryServiceImpl;
import com.example.inventoryservice.service.ProductDetailsService;
import com.example.inventoryservice.service.ProductDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(classes = InventoryServiceApplication.class)
@WebMvcTest(controllers = InventoryController.class)
//@AutoConfigureMockMvc
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryServiceImpl inventoryService;

//    @InjectMocks
//    private InventoryController inventoryController;

    @MockBean
    private ModelMapper modelMapper;


    @MockBean
    private ProductDetailsServiceImpl productDetailsService;
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    @Autowired
    private InventoryController inventoryController;

    @Before("addInventoryTest")
    public void setUp() throws Exception {
        InventoryResponseDTO inventoryResponseDTO = InventoryResponseDTO.builder()
                .id(1)
                .productDetails(ProductDetailsResponseDTO.builder()
                        .productId(1)
                        .productName("apple")
                        .productDescription("apple royal gala")
                        .build())
                .quantity(21)
                .status("AVAILABLE")
                .build();

        Inventory inventory = Inventory.builder()
                .id(1)
                .productDetails(ProductDetails.builder()
                        .productId(1)
                        .productName("apple")
                        .productDescription("apple royal gala")
                        .build())
                .quantity(21)
                .status("AVAILABLE")
                .build();

        when(inventoryService.getInventoryByProductCode(anyLong())).thenReturn(inventory);
        when(inventoryService.addInventoryByProductCode(anyLong(), anyInt())).thenReturn(inventory);
//        when(inventoryController.convertToDTO(inventory)).thenReturn(inventoryResponseDTO);
        when(modelMapper.map(inventory, InventoryResponseDTO.class)).thenReturn(inventoryResponseDTO);

    }

    //testCheckInventory
    @Test
    public void checkInventoryTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productDetails.productId").value(1))
                .andExpect(jsonPath("$.productDetails.productName").value("apple"))
                .andExpect(jsonPath("$.productDetails.productDescription").value("apple royal gala"))
                .andExpect(jsonPath("$.quantity").value(21))
                .andExpect(jsonPath("$.status").value("AVAILABLE"));

        verify(inventoryService, times(1)).getInventoryByProductCode(1L);
    }

    //testAddInventory
    @Test
    public void addInventoryTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/inventory/1?quantity=21").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productDetails.productId").value(1))
                .andExpect(jsonPath("$.productDetails.productName").value("apple"))
                .andExpect(jsonPath("$.productDetails.productDescription").value("apple royal gala"))
                .andExpect(jsonPath("$.quantity").value(21))
                .andExpect(jsonPath("$.status").value("AVAILABLE"));
    }

    //test
    @Test
    public void reduceStockTest() throws Exception {
        long productCode = 1;
        int quantity = 10;
        Inventory inventory = Inventory.builder()
                .id(1)
                .productDetails(ProductDetails.builder()
                        .productId(1)
                        .productName("apple")
                        .productDescription("apple royal gala")
                        .build())
                .quantity(21)
                .status("AVAILABLE")
                .build();

        InventoryResponseDTO inventoryResponseDTO = InventoryResponseDTO.builder()
                .id(1)
                .productDetails(ProductDetailsResponseDTO.builder()
                        .productId(1)
                        .productName("apple")
                        .productDescription("apple royal gala")
                        .build())
                .quantity(20)
                .status("AVAILABLE")
                .build();

        when(inventoryService.reduceStock(anyLong(), anyInt())).thenReturn(inventory);
        when(modelMapper.map(inventory, InventoryResponseDTO.class)).thenReturn(inventoryResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/inventory/" + productCode + "/reduce").param("quantity", quantity+""))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productDetails.productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productDetails.productName").value("apple"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productDetails.productDescription").value("apple royal gala"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("AVAILABLE"));
    }

    //testAddProductDetails
    @Test
    public void addProductDetailsTest() throws Exception {

        long productCode = 1;
        int quantity = 21;

        ProductDetailsRequestDTO productDetailsRequestDTO = ProductDetailsRequestDTO.builder()
                .productName("apple")
                .productDescription("apple royal gala")
                .build();

        ProductDetails productDetails = ProductDetails.builder()
                .productId(productCode)
                .productName("apple")
                .productDescription("apple royal gala")
                .build();

        Inventory Inventory = com.example.inventoryservice.model.Inventory.builder()
                .id(1)
                .productDetails(productDetails)
                .quantity(quantity)
                .status("AVAILABLE")
                .build();

        InventoryResponseDTO inventoryResponseDTO = InventoryResponseDTO.builder()
                .id(1)
                .productDetails(ProductDetailsResponseDTO.builder()
                        .productId(1)
                        .productName("apple")
                        .productDescription("apple royal gala")
                        .build())
                .quantity(21)
                .status("AVAILABLE")
                .build();

        String jsonBody = jacksonObjectMapper.writeValueAsString(productDetailsRequestDTO);

//        when(modelMapper.map(inventory, InventoryResponseDTO.class)).thenReturn(inventoryResponseDTO);

        when(modelMapper.map(productDetailsRequestDTO, ProductDetails.class)).thenReturn(productDetails);
        when(productDetailsService.addProductDetails(productDetails)).thenReturn(productDetails);
        when(inventoryService.addInventoryByProductCode(productCode, quantity)).thenReturn(Inventory);
        when(inventoryController.convertToDTO(Inventory)).thenReturn(inventoryResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/inventory/product?quantity=21").contentType("application/json").content(jsonBody))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productDetails.productId").value(1))
                .andExpect(jsonPath("$.productDetails.productName").value("apple"))
                .andExpect(jsonPath("$.productDetails.productDescription").value("apple royal gala"))
                .andExpect(jsonPath("$.quantity").value(21))
                .andExpect(jsonPath("$.status").value("AVAILABLE"))
                .andExpect(status().isCreated());
    }


    @Test
    public void testGetStockAvailability() throws Exception {
        long productCode = 1L;
        int quantity = 5;
        String expectedAvailability = "AVAILABLE";

        when(inventoryService.getStockAvailability(productCode, quantity)).thenReturn(expectedAvailability);

        mockMvc.perform(get("/inventory/{productCode}/availability", productCode)
                        .param("quantity", String.valueOf(quantity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedAvailability));
    }
}
