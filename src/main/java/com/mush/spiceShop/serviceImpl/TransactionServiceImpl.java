package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.domain.Stock;
import com.mush.spiceShop.domain.Transaction;
import com.mush.spiceShop.dto.TransactionInputDTO;
import com.mush.spiceShop.repository.TransactionRepository;
import com.mush.spiceShop.service.InventoryService;
import com.mush.spiceShop.service.StockService;
import com.mush.spiceShop.service.TransactionService;
import com.mush.spiceShop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private StockService stockService;
    @Autowired
    EntityManager entityManager;

    @Override
    public List<TransactionInputDTO> savePurchases(List<TransactionInputDTO> transactions) {
        transactions.forEach(trans->{
            Transaction transaction=new Transaction();
            if(trans.getId()!=null){
                transaction.setId(trans.getId());
            }
            transaction.setPricePerUnit(trans.getPricePerUnit());
            transaction.setQty(trans.getQty());
            transaction.setQtyAfterDropped(trans.getQtyAfterDropped());
            transaction.setPrice(trans.getPrice());
            transaction.setInvoice(trans.getInvoice());
            transaction.setProduct(trans.getProduct());
            transaction=transactionRepository.save(transaction);

            Inventory inventory=new Inventory();
            inventory.setProduct(trans.getProduct());
            inventory.setTransaction(transaction);
            inventory.setModifiedWeight(trans.getQtyAfterDropped());
            inventory.setModifiedDryPercent(trans.getDryPercent());
            inventory.setQuality(trans.getQuality());
            inventory.setDried(trans.getDried());
            inventory.setTransactionStatus(String.valueOf(Constants.transactionStatus.BOUGHT));
            inventory.setCreatedAt(trans.getCreatedAt());
            inventoryService.save(inventory);

            Stock stock=new Stock();
            stock.setProduct(trans.getProduct());
            stock.setQty(trans.getQty());
            stockService.save(stock);
        });
        return transactions;
    }
    @Override
    @Transactional
    public List<TransactionInputDTO> saveSales(List<TransactionInputDTO> transactions) {
        transactions.forEach(trans->{
            Transaction transaction=new Transaction();
            if(trans.getId()!=null){
                transaction.setId(trans.getId());
            }
            transaction.setPricePerUnit(trans.getPricePerUnit());
            transaction.setQty(trans.getQty());
            transaction.setQtyAfterDropped(trans.getQtyAfterDropped());
            transaction.setPrice(trans.getPrice());
            transaction.setInvoice(trans.getInvoice());
            transaction.setProduct(trans.getProduct());
            transactionRepository.save(transaction);

            Query selectQuery = entityManager.createQuery("SELECT i FROM Inventory i " +
                    "WHERE i.transactionStatus = 'BOUGHT' AND i.dried = false " +
                    "AND i.modifiedDryPercent BETWEEN :minDriedPercentage AND :maxDriedPercentage " +
                    "ORDER BY i.createdAt DESC");
            selectQuery.setParameter("minDriedPercentage", trans.getDryPercent() - 5);
            selectQuery.setParameter("maxDriedPercentage", trans.getDryPercent() + 5);

            updateInventory(selectQuery,trans.getQty());

            Stock stock=new Stock();
            stock.setProduct(trans.getProduct());
            stock.setQty(-trans.getQty());
            stockService.save(stock);
        });
        return transactions;
    }
@Transactional
    public void updateInventory(Query selectQuery,double desiredQuantity){

        List<Inventory> results = selectQuery.getResultList();

        for (Inventory inventory : results) {
            double quantity = inventory.getModifiedWeight();
            if (quantity <= desiredQuantity) {
                inventory.setTransactionStatus("SOLD");
                inventory.setDried(true);
                entityManager.persist(inventory);
                desiredQuantity -= quantity;
            } else {
                double newQuantity = quantity - desiredQuantity;
                inventory.setModifiedWeight(newQuantity);
                inventory.setTransactionStatus("SOLD");
                inventory.setDried(true);
                entityManager.persist(inventory);
                desiredQuantity = 0;
            }

            if (desiredQuantity <= 0) {
                break;
            }
        }
    }
    @Override
    public Transaction getTransactionById(Long transactionId){
        return transactionRepository.getById(transactionId);
    }
    @Override
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactionsByInvoice(Long invoiceId) {
        return transactionRepository.findAllByInvoiceId(invoiceId);
    }

    @Override
    public void deleteTransactionById(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
