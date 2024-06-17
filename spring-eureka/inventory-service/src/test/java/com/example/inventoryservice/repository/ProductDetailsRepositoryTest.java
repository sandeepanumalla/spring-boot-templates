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
import java.util.Optional;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = InventoryServiceApplication.class)
public class ProductDetailsRepositoryTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    private ProductDetails productDetails;

    @BeforeEach
    public void setup() {
        inventoryRepository.deleteAll();
        productDetailsRepository.deleteAll();

        productDetails = new ProductDetails();
        productDetails.setProductName("Test Product");
        productDetails.setProductDescription("Test Description");
    }

    @AfterEach
    public void cleanup() {
        inventoryRepository.deleteAll();
        productDetailsRepository.deleteAll();
    }

    //testsaveProductDetails
    @Test
    public void testSaveProductDetails() {
        ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);
        Assertions.assertNotNull(savedProductDetails.getProductId());
        Assertions.assertEquals("Test Product", savedProductDetails.getProductName());
        Assertions.assertEquals("Test Description", savedProductDetails.getProductDescription());
    }

    //testUpdateProductDetails
    @Test
    public void testUpdateProductDetails() {
        ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);
        savedProductDetails.setProductName("Updated Product");
        savedProductDetails.setProductDescription("Updated Description");
        ProductDetails updatedProductDetails = productDetailsRepository.save(savedProductDetails);

        Assertions.assertEquals("Updated Product", updatedProductDetails.getProductName());
        Assertions.assertEquals("Updated Description", updatedProductDetails.getProductDescription());
    }

    //testDeleteProductDetails
    @Test
    public void testDeleteProductDetails() {
        ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);
        productDetailsRepository.delete(savedProductDetails);
        Optional<ProductDetails> deletedProductDetails = productDetailsRepository.findById(savedProductDetails.getProductId());

        Assertions.assertTrue(deletedProductDetails.isEmpty());
    }

    //testFindProductDetailsById
    @Test
    public void testFindProductDetailsById() {
        ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);
        Optional<ProductDetails> foundProductDetails = productDetailsRepository.findById(savedProductDetails.getProductId());

        Assertions.assertTrue(foundProductDetails.isPresent());
        Assertions.assertEquals(savedProductDetails.getProductId(), foundProductDetails.get().getProductId());
    }

    //testFindProductDetailsByName
    @Test
    public void testFindProductDetailsByName() {
        ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);
        List<ProductDetails> foundProductDetails = productDetailsRepository.findByProductName("Test Product");

        Assertions.assertFalse(foundProductDetails.isEmpty());
        Assertions.assertEquals(1, foundProductDetails.size());
        Assertions.assertEquals(savedProductDetails.getProductId(), foundProductDetails.get(0).getProductId());
    }

    //testFindAllProductDetails
    @Test
    public void testFindAllProductDetails() {
        ProductDetails productDetails1 = new ProductDetails();
        productDetails1.setProductName("Test Product 1");
        productDetails1.setProductDescription("Test Description 1");
        productDetailsRepository.save(productDetails1);

        ProductDetails productDetails2 = new ProductDetails();
        productDetails2.setProductName("Test Product 2");
        productDetails2.setProductDescription("Test Description 2");
        productDetailsRepository.save(productDetails2);

        List<ProductDetails> allProductDetails = productDetailsRepository.findAll();

        Assertions.assertEquals(2, allProductDetails.size());
    }

    //testFindProductDetailsByInventory
    @Test
    public void testFindProductDetailsByInventory() {
        ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);

        Inventory inventory = new Inventory();
        inventory.setProductDetails(savedProductDetails);
        inventory.setQuantity(10);
        inventory.setStatus("AVAILABLE");
        inventoryRepository.save(inventory);

        Optional<Inventory> foundInventory = inventoryRepository.findByProductId(savedProductDetails.getProductId());
        Assertions.assertTrue(foundInventory.isPresent());
        Assertions.assertEquals(savedProductDetails.getProductId(), foundInventory.get().getProductDetails().getProductId());
    }

}
