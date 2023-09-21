package com.mush.spiceShop.controller;

import com.mush.spiceShop.domain.Transaction;
import com.mush.spiceShop.dto.TransactionPurchaseInputDTO;
import com.mush.spiceShop.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<List<TransactionPurchaseInputDTO>> save(@RequestBody List<TransactionPurchaseInputDTO> transactions){
        return ResponseEntity.ok(transactionService.save(transactions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long transactionId){
        Transaction transaction=transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @PutMapping
    public ResponseEntity<List<TransactionPurchaseInputDTO>> updateTransaction(List<TransactionPurchaseInputDTO> transactions){
        return ResponseEntity.ok(transactionService.save(transactions));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable("id") Long transactionId){
        transactionService.deleteTransactionById(transactionId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}