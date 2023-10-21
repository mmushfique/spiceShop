package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Inventory;

import java.time.Instant;
import java.util.List;

public interface InventoryService {
    public Inventory save(Inventory inventory);
    public List<Inventory> getAllInventories(String search, Instant fromDateTime, Instant toDateTime, String product);
    public void deleteInventoryById(Long inventoryId);
    public void updateInventory();
}
