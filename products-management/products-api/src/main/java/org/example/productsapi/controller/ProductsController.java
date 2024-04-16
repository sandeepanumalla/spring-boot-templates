package org.example.productsapi.controller;

import org.example.productsapi.service.ProductsService;
import org.example.springmodules.model.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.accepted().body(productsService.addProduct(productDto));
    }
}
