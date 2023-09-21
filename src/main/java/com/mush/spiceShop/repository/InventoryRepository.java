package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    @Query("SELECT inventory FROM Inventory inventory LEFT JOIN inventory.product product " +
            "WHERE inventory.dried='FALSE' AND inventory.transactionStatus!='SOLD'" )
    List<Inventory> findAllByNotDriedAndTransactionStatusNotSOLD();
}
