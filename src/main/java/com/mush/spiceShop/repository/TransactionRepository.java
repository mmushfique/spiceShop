package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
