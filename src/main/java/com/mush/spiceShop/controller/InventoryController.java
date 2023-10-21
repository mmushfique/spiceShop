package com.mush.spiceShop.controller;

import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventories(@RequestParam(value = "q", required = false, defaultValue = "") String search,
                                             @RequestParam(value = "fromDateTime", required = false) Instant fromDateTime,
                                             @RequestParam(value = "toDateTime", required = false) Instant toDateTime,
                                             @RequestParam(value = "product", required = false) String product){
        return inventoryService.getAllInventories(search,fromDateTime, toDateTime, product);
    }

}