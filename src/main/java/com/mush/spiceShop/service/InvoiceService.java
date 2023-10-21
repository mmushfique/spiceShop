package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Invoice;

import java.time.Instant;
import java.util.List;

public interface InvoiceService {
    public Invoice save(Invoice invoice);
    public Invoice getInvoiceById(Long invoiceId) ;
    List<Invoice> getAllInvoicePurchases(String search, Instant fromDateTime, Instant toDateTime);
    List<Invoice> getAllInvoiceSales(String search, Instant fromDateTime, Instant toDateTime);
    public void deleteInvoiceById(Long invoiceId);
}
