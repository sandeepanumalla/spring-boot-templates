package com.example.inventoryservice.repository;

import com.example.inventoryservice.InventoryServiceApplication;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.ProductDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = InventoryServiceApplication.class)
public class InventoryRepositoryTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    private Inventory inventory;
    private ProductDetails productDetails;

    @BeforeEach
    public void setup() {
        inventoryRepository.deleteAll();
        productDetailsRepository.deleteAll();

        productDetails = new ProductDetails();
        productDetails.setProductName("Test Product");
        productDetails.setProductDescription("Test Description");
        productDetails = productDetailsRepository.save(productDetails);

         inventory = new Inventory();
        inventory.setProductDetails(productDetails);
        inventory.setQuantity(10);
        inventory.setStatus("AVAILABLE");
    }

    @AfterEach
    public void tearDown() {
        inventoryRepository.deleteAll();
        productDetailsRepository.deleteAll();
    }

    //saveInventoryTests
    @Test
    public void saveInventoryTests() {
        Assertions.assertNotNull(inventory);
        Assertions.assertNotNull(inventory.getProductDetails());

        Inventory savedInventory = inventoryRepository.save(inventory);

        Assertions.assertNotNull(savedInventory.getId());
        Assertions.assertEquals(10, savedInventory.getQuantity());
        Assertions.assertEquals("AVAILABLE", savedInventory.getStatus());
    }
    //updateInventoryTests

    @Test
    public void updateInventoryTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        savedInventory.setQuantity(20);
        savedInventory.setStatus("OUT_OF_STOCK");

        Inventory updatedInventory = inventoryRepository.save(savedInventory);

        Assertions.assertEquals(20, updatedInventory.getQuantity());
        Assertions.assertEquals("OUT_OF_STOCK", updatedInventory.getStatus());
    }

    //deleteInventoryTests
    @Test
    public void deleteInventoryTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        inventoryRepository.delete(savedInventory);

        Optional<Inventory> deletedInventory = inventoryRepository.findById(savedInventory.getId());

        Assertions.assertTrue(deletedInventory.isEmpty());
    }
    //getInventoryTests
    @Test
    public void getInventoryTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        Optional<Inventory> foundInventory = inventoryRepository.findById(savedInventory.getId());

        Assertions.assertTrue(foundInventory.isPresent());
        Assertions.assertEquals(savedInventory.getId(), foundInventory.get().getId());
    }

    //getAllInventoryTests
    @Test
//    @Transactional
    public void getAllInventoryTests() {
        inventoryRepository.save(inventory);
        List<Inventory> inventories = inventoryRepository.findAll();

        Assertions.assertEquals(1, inventories.size());
    }

//    getInventoryByProductIdTests
    @Test
    @Transactional
    public void getInventoryByProductIdTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        Optional<Inventory> foundInventory = inventoryRepository.findByProductId(productDetails.getProductId());

        Assertions.assertTrue(foundInventory.isPresent());
        Assertions.assertEquals(savedInventory.getId(), foundInventory.get().getId());
    }

    //getInventoryByProductNameTests
    @Test
    public void getInventoryByProductNameTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        List<Inventory> foundInventories = inventoryRepository.findByProductName(productDetails.getProductName());

        Assertions.assertFalse(foundInventories.isEmpty());
        Assertions.assertEquals(1, foundInventories.size());
        Assertions.assertEquals(savedInventory.getId(), foundInventories.get(0).getId());
    }

    //updateInventoryByProductCodeTests
    @Test
    public void updateInventoryByProductCodeTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        Optional<Inventory> foundInventory = inventoryRepository.findByProductId(productDetails.getProductId());
        Assertions.assertTrue(foundInventory.isPresent());

        Inventory inventoryToUpdate = foundInventory.get();
        inventoryToUpdate.setQuantity(30);
        inventoryToUpdate.setStatus("LOW_STOCK");
        Inventory updatedInventory = inventoryRepository.save(inventoryToUpdate);

        Assertions.assertEquals(30, updatedInventory.getQuantity());
        Assertions.assertEquals("LOW_STOCK", updatedInventory.getStatus());
    }

    //updateInventoryByProductNameTests
    @Test
    public void updateInventoryByProductNameTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        List<Inventory> foundInventories = inventoryRepository.findByProductName(productDetails.getProductName());
        Assertions.assertFalse(foundInventories.isEmpty());

        Inventory inventoryToUpdate = foundInventories.get(0);
        inventoryToUpdate.setQuantity(50);
        inventoryToUpdate.setStatus("IN_STOCK");
        Inventory updatedInventory = inventoryRepository.save(inventoryToUpdate);

        Assertions.assertEquals(50, updatedInventory.getQuantity());
        Assertions.assertEquals("IN_STOCK", updatedInventory.getStatus());
    }

    //delectInventoryByProductCodeTests
    @Test
    public void deleteInventoryByProductCodeTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        Optional<Inventory> foundInventory = inventoryRepository.findByProductId(productDetails.getProductId());
        Assertions.assertTrue(foundInventory.isPresent());

        Inventory inventoryToDelete = foundInventory.get();
        inventoryRepository.delete(inventoryToDelete);
        Optional<Inventory> deletedInventory = inventoryRepository.findById(inventoryToDelete.getId());

        Assertions.assertTrue(deletedInventory.isEmpty());
    }

    //deleteInventoryByProductNameTests
    @Test
    public void deleteInventoryByProductNameTests() {
        Inventory savedInventory = inventoryRepository.save(inventory);
        List<Inventory> foundInventories = inventoryRepository.findByProductName(productDetails.getProductName());
        Assertions.assertFalse(foundInventories.isEmpty());

        Inventory inventoryToDelete = foundInventories.get(0);
        inventoryRepository.delete(inventoryToDelete);
        Optional<Inventory> deletedInventory = inventoryRepository.findById(inventoryToDelete.getId());

        Assertions.assertTrue(deletedInventory.isEmpty());
    }
}
