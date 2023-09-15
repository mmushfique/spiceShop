package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Inventory;

import java.util.List;

public interface InventoryService {
    public Inventory save(Inventory inventory);
    public Inventory getInventoryById(Long inventoryId);
    public List<Inventory> getAllInventories();
    public void deleteInventoryById(Long inventoryId);
}
