package com.example.inventoryservice.service;

import com.example.inventoryservice.InventoryServiceApplication;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.ProductDetails;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.repository.ProductDetailsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = InventoryServiceApplication.class)
//@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @InjectMocks
    private InventoryServiceImpl inventoryService;


    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductDetailsRepository productDetailsRepository;


    @Mock
    private Inventory inventory;
    private ProductDetails productDetails;

    @BeforeEach
    public void setup() {
//        inventoryRepository.deleteAll();
//        productDetailsRepository.deleteAll();

        productDetails = new ProductDetails();
        productDetails.setProductName("Test Product");
        productDetails.setProductDescription("Test Description");
        productDetails.setProductId(1L);

        inventory = new Inventory();
        inventory.setProductDetails(productDetails);
        inventory.setQuantity(10);
        inventory.setStatus("AVAILABLE");

        when(productDetailsRepository.save(any(ProductDetails.class))).thenReturn(productDetails);
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
        when(inventoryRepository.findByProductId(productDetails.getProductId())).thenReturn(Optional.of(inventory));
    }

//    @AfterEach
//    public void cleanup() {
//        inventoryRepository.deleteAll();
//        productDetailsRepository.deleteAll();
//    }


    //testGetInventoryByProductCode
    @Test
    public void testGetInventoryByProductCode() throws Exception {
        Inventory foundInventory = inventoryService.getInventoryByProductCode(productDetails.getProductId());
        Assertions.assertNotNull(foundInventory);
        Assertions.assertEquals("Test Product", foundInventory.getProductDetails().getProductName());
    }

    //testUpdateInventoryByProductCode
    @Test
    public void testUpdateInventoryByProductCode() {
        productDetails.setProductName("Updated Product");
        Inventory updatedInventory = inventoryService.updateInventoryByProductCode(productDetails.getProductId(), productDetails);
        Assertions.assertNotNull(updatedInventory);
        Assertions.assertEquals("Updated Product", updatedInventory.getProductDetails().getProductName());
    }

    //testDeleteInventoryByProductCode
    @Test
    public void testDeleteInventoryByProductCode() {
        inventoryService.deleteInventoryByProductCode(productDetails.getProductId());
        Optional<Inventory> deletedInventory = inventoryRepository.findByProductId(productDetails.getProductId());
        Assertions.assertTrue(deletedInventory.isEmpty());
    }

    //testAddInventoryByProductCode
    @Test
    public void testAddInventoryByProductCode() {
        ProductDetails newProductDetails = new ProductDetails();
        newProductDetails.setProductName("New Product");
        newProductDetails.setProductDescription("New Description");
        newProductDetails.setProductId(2L);

        Inventory newInventoryStub = Inventory.builder()
                .productDetails(newProductDetails)
                .status("AVAILABLE")
                .quantity(5)
                .build();

        when(productDetailsRepository.findById(anyLong())).thenReturn(Optional.of(newProductDetails));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(newInventoryStub);

        Inventory newInventory = inventoryService.addInventoryByProductCode(newProductDetails.getProductId(), 5);
        Assertions.assertNotNull(newInventory);
        Assertions.assertEquals("New Product", newInventory.getProductDetails().getProductName());
    }

    @Test
    public void testReduceStockInsufficientStock() {
        when(inventoryRepository.findByProductId(productDetails.getProductId())).thenReturn(Optional.of(inventory));

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> inventoryService.reduceStock(productDetails.getProductId(), 11));
        Assertions.assertEquals("Not enough stock available", exception.getMessage());
    }

    @Test
    public void testReduceStockWithSufficientStock() {
        when(inventoryRepository.findByProductId(productDetails.getProductId())).thenReturn(Optional.of(inventory));

        Inventory updatedInventory = inventoryService.reduceStock(productDetails.getProductId(), 5);
        Assertions.assertNotNull(updatedInventory);
        Assertions.assertEquals(5, updatedInventory.getQuantity());
    }

    @Test
    public void testReduceStockNonExistingProductCode() {
        when(inventoryRepository.findByProductId(999L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> inventoryService.reduceStock(999L, 5)
        );

        Assertions.assertEquals("Inventory not found for product code: 999", exception.getMessage());
    }


    @Test
    public void testGetStockAvailability() throws Exception {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setStatus("AVAILABLE");
        inventory.setQuantity(10);

        when(inventoryRepository.findByProductId(anyLong())).thenReturn(Optional.of(inventory));

        // Act
        String status = inventoryService.getStockAvailability(1L, 5);

        // Assert
        assertEquals("AVAILABLE", status);

        // Act
        status = inventoryService.getStockAvailability(1L, 15);

        // Assert
        assertEquals("OUT_OF_STOCK", status);
    }
}
