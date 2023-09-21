package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Transaction;
import com.mush.spiceShop.dto.TransactionPurchaseInputDTO;

import java.util.List;

public interface TransactionService {
    public List<TransactionPurchaseInputDTO> save(List<TransactionPurchaseInputDTO> transactions);
    public Transaction getTransactionById(Long transactionId);
    public List<Transaction> getAllTransactions();
    public void deleteTransactionById(Long transactionId);
}
