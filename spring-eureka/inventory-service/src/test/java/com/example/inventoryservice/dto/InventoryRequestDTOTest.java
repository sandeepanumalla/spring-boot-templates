package com.example.inventoryservice.dto;

import com.example.inventoryservice.InventoryServiceApplication;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = InventoryServiceApplication.class)
public class InventoryRequestDTOTest {

    @Test
    public void testInventoryRequestDTO() {
        InventoryRequestDTO dto = new InventoryRequestDTO(1L, 10, "AVAILABLE");

        assertEquals(1L, dto.getProductId());
        assertEquals(10, dto.getQuantity());
        assertEquals("AVAILABLE", dto.getStatus());

        dto.setProductId(2L);
        dto.setQuantity(5);
        dto.setStatus("OUT_OF_STOCK");

        assertEquals(2L, dto.getProductId());
        assertEquals(5, dto.getQuantity());
        assertEquals("OUT_OF_STOCK", dto.getStatus());

//        EqualsVerifier.simple().forClass(InventoryRequestDTO.class).verify();
    }

    @Test
    public void testInventoryResponseDTO() {
        ProductDetailsResponseDTO productDetails = new ProductDetailsResponseDTO(1L, "Product", "Description");
        InventoryResponseDTO dto = new InventoryResponseDTO(1L, productDetails, 10, "AVAILABLE");

        assertEquals(1L, dto.getId());
        assertEquals(productDetails, dto.getProductDetails());
        assertEquals(10, dto.getQuantity());
        assertEquals("AVAILABLE", dto.getStatus());

        dto.setId(2L);
        dto.setQuantity(5);
        dto.setStatus("OUT_OF_STOCK");

        assertEquals(2L, dto.getId());
        assertEquals(5, dto.getQuantity());
        assertEquals("OUT_OF_STOCK", dto.getStatus());

//        EqualsVerifier.simple().forClass(InventoryResponseDTO.class).verify();
    }

    @Test
    public void testProductDetailsRequestDTO() {
        ProductDetailsRequestDTO dto = new ProductDetailsRequestDTO(3,"Product", "Description");

        assertEquals("Product", dto.getProductName());
        assertEquals("Description", dto.getProductDescription());

        dto.setProductName("Updated Product");
        dto.setProductDescription("Updated Description");

        assertEquals("Updated Product", dto.getProductName());
        assertEquals("Updated Description", dto.getProductDescription());

//        EqualsVerifier.simple().forClass(ProductDetailsRequestDTO.class).verify();
    }

    @Test
    public void testProductDetailsResponseDTO() {
        ProductDetailsResponseDTO dto = new ProductDetailsResponseDTO(1L, "Product", "Description");

        assertEquals(1L, dto.getProductId());
        assertEquals("Product", dto.getProductName());
        assertEquals("Description", dto.getProductDescription());

        dto.setProductId(2L);
        dto.setProductName("Updated Product");
        dto.setProductDescription("Updated Description");

        assertEquals(2L, dto.getProductId());
        assertEquals("Updated Product", dto.getProductName());
        assertEquals("Updated Description", dto.getProductDescription());

//        EqualsVerifier.simple().forClass(ProductDetailsResponseDTO.class).verify();
    }
}
