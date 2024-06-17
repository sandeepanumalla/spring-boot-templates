package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.ProductDetails;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.repository.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    private final ModelMapper modelMapper;
    private final ProductDetailsRepository productDetailsRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public String getStockAvailability(long productCode, int quantity) throws Exception {
        Inventory inventory = inventoryRepository.findByProductId(productCode).orElseThrow(() -> new Exception("product not found"));
        return inventory.getStatus().equals("AVAILABLE") && inventory.getQuantity() >= quantity ? "AVAILABLE" : "OUT_OF_STOCK";
    }

    @Override
    public Inventory getInventoryByProductCode(long productCode) throws Exception {
        return inventoryRepository.findByProductId(productCode).orElseThrow(() -> new IllegalArgumentException("Inventory not found"));
    }

    @Override
    public Inventory updateInventoryByProductCode(long productCode, ProductDetails productDetails) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productCode);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            inventory.setProductDetails(productDetails);
            return inventoryRepository.save(inventory);
        }
        return null;
    }

    @Override
    public void deleteInventoryByProductCode(long productCode) {
//        inventoryRepository.deleteByProductId(productCode);
    }

    @Override
    public Inventory addInventoryByProductCode(long productCode, int quantity) {
        ProductDetails productDetails2 = productDetailsRepository.findById(productCode).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        Inventory inventory = Inventory.builder()
                .productDetails(productDetails2)
                .status("AVAILABLE")
                .quantity(quantity)
                .build();
        Inventory savedInventory = inventoryRepository.save(inventory);
        return savedInventory;
    }

    @Override
    public Iterable<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory reduceStock(long productCode, int quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productCode);
        if(inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            if (inventory.getQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock available");
            }
            inventory.setQuantity(inventory.getQuantity() - quantity);
            return inventoryRepository.save(inventory);
        } else {
            throw new IllegalArgumentException("Inventory not found for product code: " + productCode);
        }
    }
}
