package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.repository.InventoryRepository;
import com.mush.spiceShop.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Scheduled(fixedRate = 3600000) // 1 hour = 3,600,000 milliseconds
    public void updateInventory() {
        List<Inventory> wetProducts = inventoryRepository.findAllByNotDriedAndTransactionStatusNotSOLD(); //95% scenario

        List<Inventory> updatedProducts=wetProducts.stream().map(product->{
            double dryingRateFrom0=product.getProduct().getRateFROM0();
            double dryingRateFrom50=product.getProduct().getRateFROM50();
            double initialWeight= product.getModifiedWeight();
            double initialDriedPercentage= product.getModifiedDryPercent();
            double reducedWeight;
            if(initialDriedPercentage<50){
                reducedWeight=(dryingRateFrom0/(100*60*60))*initialWeight;
            }else{
                reducedWeight=(dryingRateFrom50/(100*60*60))*initialWeight;
            }

            double modifiedWeight=initialWeight-reducedWeight;
            double modifiedDriedPercentage=initialDriedPercentage-(dryingRateFrom0/(100*60*60));

            if (modifiedDriedPercentage >= 97.0) {
                product.setDried(true);
            }

            product.setModifiedWeight(modifiedWeight);
            product.setModifiedDryPercent(modifiedDriedPercentage);
            return product;
        }).collect(Collectors.toList());

        updatedProducts.forEach(product->{
            inventoryRepository.save(product);
        });
    }
}
