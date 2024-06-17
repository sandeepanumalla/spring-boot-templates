package com.example.inventoryservice.service;

import com.example.inventoryservice.InventoryServiceApplication;
import com.example.inventoryservice.model.ProductDetails;
import com.example.inventoryservice.repository.ProductDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = InventoryServiceApplication.class)
public class ProductDetailsServiceTest {

    @InjectMocks
    private ProductDetailsServiceImpl productDetailsService;

    @Mock
    private ProductDetailsRepository productDetailsRepository;

    @Mock
    private ProductDetails productDetails;

    @BeforeEach
    public void setup() {
        productDetails = new ProductDetails();
        productDetails.setProductName("Test Product");
        productDetails.setProductDescription("Test Description");
        productDetails.setProductId(1L);
        when(productDetailsRepository.save(any(ProductDetails.class))).thenReturn(productDetails);
    }

    // Test for getProductDetailsByProductCode
    @Test
    public void testGetProductDetailsByProductCode() {
        when(productDetailsRepository.findById(productDetails.getProductId())).thenReturn(Optional.of(productDetails));
        ProductDetails foundProductDetails = productDetailsService.getProductDetailsByProductCode(productDetails.getProductId());
        Assertions.assertNotNull(foundProductDetails);
        Assertions.assertEquals("Test Product", foundProductDetails.getProductName());
    }

    // Test for getProductDetailsByProductCode with non-existing product code
    @Test
    public void testGetProductDetailsByNonExistingProductCode() {
        when(productDetailsRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productDetailsService.getProductDetailsByProductCode(999L)
        );

        Assertions.assertEquals("Product details not found for product code: 999", exception.getMessage());
    }

    // Test for updateProductDetailsByProductCode
    @Test
    public void testUpdateProductDetailsByProductCode() {
        productDetails.setProductName("Updated Product");
        ProductDetails updatedProductDetails = productDetailsService.updateProductDetailsByProductCode(productDetails.getProductId(), productDetails);
        Assertions.assertNotNull(updatedProductDetails);
        Assertions.assertEquals("Updated Product", updatedProductDetails.getProductName());
    }

    // Test for updateProductDetailsByProductCode with non-existing product code
    @Test
    public void testUpdateProductDetailsByNonExistingProductCode() {
        when(productDetailsRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productDetailsService.updateProductDetailsByProductCode(999L, productDetails)
        );

        Assertions.assertEquals("Product details not found for product code: 999", exception.getMessage());
    }

    // Test for deleteProductDetailsByProductCode
    @Test
    public void testDeleteProductDetailsByProductCode() {
        productDetailsService.deleteProductDetailsByProductCode(productDetails.getProductId());
        Optional<ProductDetails> deletedProductDetails = productDetailsRepository.findById(productDetails.getProductId());
        Assertions.assertTrue(deletedProductDetails.isEmpty());
    }

    // Test for deleteProductDetailsByProductCode with non-existing product code
    @Test
    public void testDeleteProductDetailsByNonExistingProductCode() {
        when(productDetailsRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> productDetailsService.deleteProductDetailsByProductCode(999L)
        );

        Assertions.assertEquals("Product details not found for product code: 999", exception.getMessage());
    }

    // Test for addProductDetailsByProductCode
    @Test
    public void testAddProductDetailsByProductCode() {
        ProductDetails newProductDetails = new ProductDetails();
        newProductDetails.setProductName("New Product");
        newProductDetails.setProductDescription("New Description");
//        newProductDetails.setProductId(2L);

        when(productDetailsRepository.save(any(ProductDetails.class))).thenReturn(newProductDetails);

        ProductDetails addedProductDetails = productDetailsService.addProductDetails(newProductDetails);
        Assertions.assertNotNull(addedProductDetails);
        Assertions.assertEquals("New Product", addedProductDetails.getProductName());
    }
}
