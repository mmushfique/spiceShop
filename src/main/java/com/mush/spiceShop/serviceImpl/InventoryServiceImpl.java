package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.reposiory.InventoryRepository;
import com.mush.spiceShop.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
    @Override
    public Inventory getInventoryById(Long inventoryId){
        return inventoryRepository.getById(inventoryId);
    }
    @Override
    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }
    @Override
    public void deleteInventoryById(Long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }
}
