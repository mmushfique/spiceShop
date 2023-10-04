package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query("SELECT invoice FROM Invoice invoice " +
//            "LEFT JOIN Supplier supplier " +
            "WHERE invoice.tradeType='BOUGHT'")
    List<Invoice> getAllInvoicePurchases();

    @Query("SELECT invoice FROM Invoice invoice " +
//            "LEFT JOIN Buyer buyer " +
            "WHERE invoice.tradeType='SOLD'")
    List<Invoice> getAllInvoiceSales();
}
