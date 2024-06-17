package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.ProductDetails;
import org.springframework.stereotype.Service;


public interface InventoryService {
    // create a method to get the inventory by product code
    public Inventory getInventoryByProductCode(long productCode) throws Exception;

    // create a method to update the inventory by product code
    public Inventory updateInventoryByProductCode(long productCode, ProductDetails productDetails);

    // create a method to delete the inventory by product code
    public void deleteInventoryByProductCode(long productCode);

    // create a method to add the inventory by product code
    public Inventory addInventoryByProductCode(long productCode, int quantity);

    // create a method to get all the inventories
    public Iterable<Inventory> getAllInventories();

    Inventory reduceStock(long productCode, int quantity);

    public String getStockAvailability(long productCode, int quantity) throws Exception;


}


