package com.mush.spiceShop.service;

import com.mush.spiceShop.dto.DashboardTotalCountDTO;
import com.mush.spiceShop.repository.InvoiceRepository;
import com.mush.spiceShop.repository.PersonRepository;
import com.mush.spiceShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    public DashboardTotalCountDTO getAllTotalCounts() {
        int supplier=personRepository.countAllPersonByType("SUPPLIER");
        int buyer=personRepository.countAllPersonByType("BUYER");
        int purchasedInvoice=invoiceRepository.countAllInvoiceByTradeType("BOUGHT");
        int salesInvoice=invoiceRepository.countAllInvoiceByTradeType("SOLD");
        double totalPurchased=invoiceRepository.getTotalPurchasedAmount();
        double totalSold=invoiceRepository.getTotalSoldAmount();
        DashboardTotalCountDTO dashboardTotalCountDTO=new DashboardTotalCountDTO(
                supplier,buyer,purchasedInvoice,salesInvoice,totalPurchased,totalSold
        );
        return dashboardTotalCountDTO;
    }
}
