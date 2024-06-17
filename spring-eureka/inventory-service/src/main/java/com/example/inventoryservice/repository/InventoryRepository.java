package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE i.productDetails.productId = :productId")
    Optional<Inventory> findByProductId(long productId);

    @Query("SELECT i FROM Inventory i WHERE i.productDetails.productName = :productName")
    List<Inventory> findByProductName(String productName);
}
