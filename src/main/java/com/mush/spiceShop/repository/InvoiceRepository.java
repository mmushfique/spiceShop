package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query("SELECT invoice FROM Invoice invoice " +
            "LEFT JOIN Person supplier ON invoice.person.id=supplier.id " +
            "WHERE invoice.tradeType='BOUGHT'")
    List<Invoice> getAllInvoicePurchases();

    @Query("SELECT invoice FROM Invoice invoice " +
            "LEFT JOIN Person buyer ON invoice.person.id=buyer.id " +
            "WHERE invoice.tradeType='SOLD'")
    List<Invoice> getAllInvoiceSales();

    int countAllInvoiceByTradeType(String type);

    @Query("SELECT SUM(i.total) FROM Invoice i WHERE i.tradeType='BOUGHT'")
    Double getTotalPurchasedAmount();

    @Query("SELECT SUM(i.total) FROM Invoice i WHERE i.tradeType='SOLD'")
    Double getTotalSoldAmount();
}
