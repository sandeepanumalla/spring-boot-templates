package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long>{
    List<ProductDetails> findByProductName(String testProduct);
}
