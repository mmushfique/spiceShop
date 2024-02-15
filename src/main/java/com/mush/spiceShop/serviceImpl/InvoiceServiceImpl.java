package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Invoice;
import com.mush.spiceShop.repository.InvoiceRepository;
import com.mush.spiceShop.service.InvoiceService;
import com.mush.spiceShop.utils.BuildQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    @Override
    public Invoice getInvoiceById(Long invoiceId){
        return invoiceRepository.getById(invoiceId);
    }
    @Override
    public List<Invoice> getAllInvoicePurchases(String search, Instant fromDateTime, Instant toDateTime) {
        String fromQry=" FROM Invoice invoice LEFT JOIN Person supplier ON invoice.person.id=supplier.id WHERE invoice.tradeType='BOUGHT' ";

        if(toDateTime==null) toDateTime= Instant.now().truncatedTo(java.time.temporal.ChronoUnit.DAYS);
        if(fromDateTime==null) fromDateTime= toDateTime.minus(Duration.ofDays(6));


        if(search!=null && !search.isEmpty()){
            String searchQry=" invoice.person.name LIKE '%"+search+"%' ";
            fromQry= BuildQuery.checkAndOr(fromQry,searchQry);
        }

        fromQry=BuildQuery.checkBetween(fromDateTime,toDateTime,fromQry," invoice.createdAt ");

        String qry= "SELECT invoice " + fromQry;
        System.out.println(qry);
        Query query = entityManager.createQuery(qry);
        List<Invoice> invoiceList=query.getResultList();
        return invoiceList;
    }
    @Override
    public List<Invoice> getAllInvoiceSales(String search, Instant fromDateTime, Instant toDateTime) {
        String fromQry=" FROM Invoice invoice LEFT JOIN Person buyer ON invoice.person.id=buyer.id WHERE invoice.tradeType='SOLD' ";

        if(toDateTime==null) toDateTime= Instant.now().truncatedTo(java.time.temporal.ChronoUnit.DAYS);
        if(fromDateTime==null) fromDateTime= toDateTime.minus(Duration.ofDays(6));


        if(search!=null && !search.isEmpty()){
            String searchQry=" invoice.person.name LIKE '%"+search+"%' ";
            fromQry= BuildQuery.checkAndOr(fromQry,searchQry);
        }

        fromQry=BuildQuery.checkBetween(fromDateTime,toDateTime,fromQry," invoice.createdAt ");

        String qry= "SELECT invoice " + fromQry;
        System.out.println(qry);
        Query query = entityManager.createQuery(qry);
        List<Invoice> invoiceList=query.getResultList();
        return invoiceList;
    }
    @Override
    public void deleteInvoiceById(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }
}
