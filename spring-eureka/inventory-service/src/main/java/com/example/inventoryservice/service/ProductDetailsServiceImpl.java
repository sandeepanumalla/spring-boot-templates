package com.example.inventoryservice.service;


import com.example.inventoryservice.model.ProductDetails;
import com.example.inventoryservice.repository.ProductDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService{

    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    @Override
    public ProductDetails getProductDetailsByProductCode(long productCode) {
        Optional<ProductDetails> productDetailsOpt = productDetailsRepository.findById(productCode);
        if (productDetailsOpt.isPresent()) {
            return productDetailsOpt.get();
        } else {
            throw new IllegalArgumentException("Product details not found for product code: " + productCode);
        }
    }

    @Override
    public ProductDetails updateProductDetailsByProductCode(long productCode, ProductDetails productDetails) {
        Optional<ProductDetails> productDetailsOpt = productDetailsRepository.findById(productCode);
        if (productDetailsOpt.isPresent()) {
            ProductDetails existingProductDetails = productDetailsOpt.get();
            existingProductDetails.setProductName(productDetails.getProductName());
            existingProductDetails.setProductDescription(productDetails.getProductDescription());
            return productDetailsRepository.save(existingProductDetails);
        } else {
            throw new IllegalArgumentException("Product details not found for product code: " + productCode);
        }
    }

    @Override
    public void deleteProductDetailsByProductCode(long productCode) {
        Optional<ProductDetails> productDetailsOpt = productDetailsRepository.findById(productCode);
        if (productDetailsOpt.isPresent()) {
            productDetailsRepository.delete(productDetailsOpt.get());
        } else {
            throw new IllegalArgumentException("Product details not found for product code: " + productCode);
        }
    }

    @Override
    public ProductDetails addProductDetails( ProductDetails productDetails) {
        return productDetailsRepository.save(productDetails);
    }
}
