package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Transaction;
import com.mush.spiceShop.dto.TransactionInputDTO;

import java.util.List;

public interface TransactionService {
    public List<TransactionInputDTO> save(List<TransactionInputDTO> transactions);
    public Transaction getTransactionById(Long transactionId);
    public List<Transaction> getAllTransactions();
    public void deleteTransactionById(Long transactionId);
}
