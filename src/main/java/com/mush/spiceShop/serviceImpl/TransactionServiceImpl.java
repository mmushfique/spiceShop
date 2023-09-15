package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.domain.Transaction;
import com.mush.spiceShop.dto.TransactionInputDTO;
import com.mush.spiceShop.reposiory.TransactionRepository;
import com.mush.spiceShop.service.InventoryService;
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

    @Override
    public List<TransactionInputDTO> save(List<TransactionInputDTO> transactions) {
        transactions.forEach(trans->{
            Transaction transaction=new Transaction();
            if(trans.getId()!=null){
                transaction.setId(trans.getId());
            }
            transaction.setTransactionType(trans.getTransactionType());
            transaction.setPricePerUnit(trans.getPricePerUnit());
            transaction.setQty(trans.getQty());
            transaction.setQtyAfterDropped(trans.getQtyAfterDropped());
            transaction.setPrice(trans.getPrice());
            transaction.setInvoice(trans.getInvoice());
            transaction.setProduct(trans.getProduct());
            Transaction transactionResponse=transactionRepository.save(transaction);

            Inventory inventory=new Inventory();
            inventory.setWeight(trans.getQtyAfterDropped());
            inventory.setModifiedWeight(trans.getQtyAfterDropped());
            inventory.setDried(trans.getDried());
            inventory.setDryPercent(trans.getDryPercent());
            inventory.setModifiedDryPercent(trans.getDryPercent());
            inventory.setTransaction(transactionResponse);
            inventoryService.save(inventory);
        });
        return transactions;
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
    public void deleteTransactionById(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
