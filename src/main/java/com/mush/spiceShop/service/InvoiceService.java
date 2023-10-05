package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Invoice;

import java.util.List;

public interface InvoiceService {
    public Invoice save(Invoice invoice);
    public Invoice getInvoiceById(Long invoiceId) ;
    List<Invoice> getAllInvoicePurchases();
    List<Invoice> getAllInvoiceSales();
    public void deleteInvoiceById(Long invoiceId);
}
