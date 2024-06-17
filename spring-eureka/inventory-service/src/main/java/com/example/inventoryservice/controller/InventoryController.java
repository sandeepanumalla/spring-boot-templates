package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryRequestDTO;
import com.example.inventoryservice.dto.InventoryResponseDTO;
import com.example.inventoryservice.dto.ProductDetailsRequestDTO;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.ProductDetails;
import com.example.inventoryservice.service.InventoryServiceImpl;
import com.example.inventoryservice.service.ProductDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryService;
    private final ModelMapper modelMapper;
    private final ProductDetailsServiceImpl productDetailsServiceImpl;


    // API for getting the inventory
    @GetMapping("/{productCode}")
    public ResponseEntity<InventoryResponseDTO> checkInventory(@PathVariable long productCode) throws Exception {
        Inventory inventory = inventoryService.getInventoryByProductCode(productCode);
        InventoryResponseDTO responseDTO = convertToDTO(inventory);
        return ResponseEntity.ok(responseDTO);
    }

//     API for updating the inventory
//    @PutMapping("/{productCode}")
//    public ResponseEntity<InventoryResponseDTO> updateInventory(@PathVariable long productCode, @RequestBody ProductDetailsRequestDTO productDetailsDTO) {
//        Inventory updatedInventory = inventoryService.updateInventoryByProductCode(productCode, productDetailsDTO);
//        InventoryResponseDTO responseDTO = convertToDTO(updatedInventory);
//        return ResponseEntity.ok(responseDTO);
//    }

//     API for adding new inventory
    @PostMapping("/{productCode}")
    public ResponseEntity<InventoryResponseDTO> addInventory(@RequestParam(value = "quantity", required = false, defaultValue = "5") int quantity, @PathVariable long productCode) {
        Inventory newInventory = null;
        newInventory = inventoryService.addInventoryByProductCode(productCode, quantity);
        InventoryResponseDTO responseDTO = convertToDTO(newInventory);
        return ResponseEntity.ok(responseDTO);
    }

    // API for deleting the inventory
    @DeleteMapping("/{productCode}")
    public ResponseEntity<Void> deleteInventory(@PathVariable long productCode) {
        inventoryService.deleteInventoryByProductCode(productCode);
        return ResponseEntity.noContent().build();
    }

    // API for getting all inventories
    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> getAllInventories() {
        List<Inventory> inventories = (List<Inventory>) inventoryService.getAllInventories();
        List<InventoryResponseDTO> responseDTOs = inventories.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }


    // API for reducing the stock of a product
    @PutMapping("/{productCode}/reduce")
    public ResponseEntity<InventoryResponseDTO> reduceStock(@RequestParam(value = "quantity", required = true) int quantity, @PathVariable long productCode) {
        Inventory updatedInventory = inventoryService.reduceStock(productCode, quantity);
        InventoryResponseDTO responseDTO = convertToDTO(updatedInventory);
        return ResponseEntity.ok(responseDTO);
    }

    protected InventoryResponseDTO convertToDTO(Inventory inventory) {
        return modelMapper.map(inventory, InventoryResponseDTO.class);
    }

    protected Inventory convertToInventoryEntity(InventoryRequestDTO inventoryRequestDTO) {
        return modelMapper.map(inventoryRequestDTO, Inventory.class);
    }

    protected ProductDetails convertToProductDetailsEntity(ProductDetailsRequestDTO productDetailsRequestDTO) {
        return modelMapper.map(productDetailsRequestDTO, ProductDetails.class);
    }

    @PostMapping("/product")
    public ResponseEntity<InventoryResponseDTO> addProductDetails(@RequestParam int quantity, @RequestBody ProductDetailsRequestDTO productDetailsRequestDTO) {
        ProductDetails productDetails = convertToProductDetailsEntity(productDetailsRequestDTO);
        ProductDetails savedProductDetails = productDetailsServiceImpl.addProductDetails(productDetails);
        Inventory inventory = inventoryService.addInventoryByProductCode(savedProductDetails.getProductId(), quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(inventory));
    }

    @GetMapping("/{productCode}/availability")
    public ResponseEntity<String> getStockAvailability(@PathVariable long productCode, @RequestParam int quantity) throws Exception {
        String availability = inventoryService.getStockAvailability(productCode, quantity);
        return ResponseEntity.ok(availability);
    }

}
