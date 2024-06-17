package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.ProductDetails;
import com.example.inventoryservice.service.ProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    // API for getting the product details
    public String getProductDetails() {

        return "";
    }

    // API for updating the product details

    // API for adding new product details

    // API for deleting the product details

    // API for getting all product details
    // API for reducing the stock of a product

//    @GetMapping
//    public String getProductDetails() {
//        return "Product details";
//    }
}
