package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Invoice;
import com.mush.spiceShop.repository.InvoiceRepository;
import com.mush.spiceShop.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    @Override
    public Invoice getInvoiceById(Long invoiceId){
        return invoiceRepository.getById(invoiceId);
    }
    @Override
    public List<Invoice> getAllInvoicePurchases() {
        return invoiceRepository.getAllInvoicePurchases();
    }
    @Override
    public List<Invoice> getAllInvoiceSales() {
        return invoiceRepository.getAllInvoiceSales();
    }
    @Override
    public void deleteInvoiceById(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }
}
