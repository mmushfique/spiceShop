package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.domain.Stock;
import com.mush.spiceShop.repository.InventoryRepository;
import com.mush.spiceShop.service.InventoryService;
import com.mush.spiceShop.service.StockService;
import com.mush.spiceShop.utils.BuildQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StockService stockService;
    @Autowired
    private EntityManager entityManager;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
    @Override
    public List<Inventory> getAllInventories(String search,Instant fromDateTime,Instant toDateTime,String product){
        String fromQry=" FROM Inventory inventory ";

        if(toDateTime==null) toDateTime= Instant.now().truncatedTo(java.time.temporal.ChronoUnit.DAYS);;
        if(fromDateTime==null) fromDateTime= toDateTime.minus(Duration.ofDays(6));

        if(product!=null && !product.isEmpty()) {
            fromQry += " LEFT JOIN inventory.product WHERE inventory.transactionStatus='BOUGHT' ";
        }

        if(search!=null && !search.isEmpty()){
            String searchQry=" inventory.quality LIKE '%"+search+"%' OR inventory.product.name LIKE '%"+search+"%' ";
            fromQry= BuildQuery.checkAndOr(fromQry,searchQry);
        }

        fromQry=BuildQuery.checkBetween(fromDateTime,toDateTime,fromQry," inventory.createdAt ");

        if(product!=null && !product.isEmpty()) {
            fromQry = BuildQuery.checkAndOr(fromQry,"inventory.product.name='"+product+"'");
        }

        String qry= "SELECT inventory " + fromQry;
        System.out.println(qry);
        Query query = entityManager.createQuery(qry);
        List<Inventory> inventoryList=query.getResultList();
        return inventoryList;
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

            Stock stock = new Stock();
            stock.setProduct(product.getProduct());

            if (modifiedDriedPercentage >= 97.0) {
                product.setDried(true);
                stock.setQty(-initialWeight);
                stockService.save(stock);

                stock.setQty(modifiedWeight);
                stockService.saveDried(stock);
            }else {
                stock.setQty(-reducedWeight);
                stockService.save(stock);
            }

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
