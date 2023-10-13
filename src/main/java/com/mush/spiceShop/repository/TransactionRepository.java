package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByInvoiceId(Long invoiceId);
}
