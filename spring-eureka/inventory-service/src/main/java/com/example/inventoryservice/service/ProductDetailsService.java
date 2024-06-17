package com.example.inventoryservice.service;

import com.example.inventoryservice.model.ProductDetails;
import org.springframework.stereotype.Service;


public interface ProductDetailsService {
    // create a method to get the product details by product code
    public ProductDetails getProductDetailsByProductCode(long productCode);

    // create a method to update the product details by product code
    public ProductDetails updateProductDetailsByProductCode(long productCode, ProductDetails productDetails);

    // create a method to delete the product details by product code
    public void deleteProductDetailsByProductCode(long productCode);

    // create a method to add the product details by product code
    public ProductDetails addProductDetails(ProductDetails productDetails);
}
