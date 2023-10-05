package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.domain.Stock;
import com.mush.spiceShop.domain.Transaction;
import com.mush.spiceShop.dto.TransactionInputDTO;
import com.mush.spiceShop.repository.TransactionRepository;
import com.mush.spiceShop.service.InventoryService;
import com.mush.spiceShop.service.StockService;
import com.mush.spiceShop.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private StockService stockService;

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
            inventory.setTransaction(transaction);
            inventory.setModifiedWeight(trans.getQtyAfterDropped());
            inventory.setDried(trans.getDried());
            inventory.setModifiedDryPercent(trans.getDryPercent());
            inventoryService.save(inventory);

            Stock stock=new Stock();
            stock.setProduct(trans.getProduct());
            stock.setQty(trans.getQty());
            stockService.save(stock);
        });
        return transactions;
    }
    @Override
    public List<TransactionInputDTO> saveSales(List<TransactionInputDTO> transactions) {
        return null;
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
