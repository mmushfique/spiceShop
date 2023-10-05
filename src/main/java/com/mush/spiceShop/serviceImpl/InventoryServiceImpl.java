package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.domain.Stock;
import com.mush.spiceShop.repository.InventoryRepository;
import com.mush.spiceShop.service.InventoryService;
import com.mush.spiceShop.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StockService stockService;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
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

//        List<Inventory> updatedProducts=wetProducts.stream().map(product->{
            wetProducts.forEach(product->{
            double dryingRateFrom0=product.getProduct().getRateFrom0();
            double dryingRateFrom50=product.getProduct().getRateFrom50();
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

            Stock stock=new Stock();
            stock.setProduct(product.getProduct());
            stock.setQty(-reducedWeight);
            stockService.save(stock);

            product.setModifiedWeight(modifiedWeight);
            product.setModifiedDryPercent(modifiedDriedPercentage);
            inventoryRepository.save(product);

//            return product;
//        }).collect(Collectors.toList());
//
//        updatedProducts.forEach(product->{
//            inventoryRepository.save(product);
        });
    }
}
