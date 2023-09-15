package com.mush.spiceShop.controller;

import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> save(@RequestBody Inventory inventory){
        return ResponseEntity.ok(inventoryService.save(inventory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") Long inventoryId){
        Inventory inventory=inventoryService.getInventoryById(inventoryId);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping
    public List<Inventory> getAllInventories(){
        return inventoryService.getAllInventories();
    }

    @PutMapping
    public ResponseEntity<Inventory> updateInventory(Inventory inventory){
        return ResponseEntity.ok(inventoryService.save(inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryById(@PathVariable("id") Long inventoryId){
        inventoryService.deleteInventoryById(inventoryId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}