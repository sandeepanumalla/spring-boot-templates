package org.example.productsapi.service;

import org.example.springmodules.model.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {


    public ProductDto addProduct(ProductDto productDto) {
        return new ProductDto(productDto.getName(), productDto.getDescription());
    }
}
