package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.dto.DashboardTotalCountDTO;
import com.mush.spiceShop.dto.DashboardTransactionDTO;
import com.mush.spiceShop.repository.InvoiceRepository;
import com.mush.spiceShop.repository.PersonRepository;
import com.mush.spiceShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    private EntityManager entityManager;
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

    public List<DashboardTransactionDTO> getAllTransactionsByDateAndTradeType(Long productId) {
        Instant toDateTime= Instant.now().truncatedTo(java.time.temporal.ChronoUnit.DAYS);;
        Instant fromDateTime= toDateTime.minus(Duration.ofDays(30));

        String qry ="SELECT DATE(i.createdAt) AS TransactionDate, " +
                " AVG(CASE WHEN i.tradeType = 'BOUGHT' THEN t.pricePerUnit ELSE NULL END) AS AvgPurchasePrice, " +
                " AVG(CASE WHEN i.tradeType = 'SOLD' THEN t.pricePerUnit ELSE NULL END) AS AvgSalesPrice " +
                " FROM Transaction t INNER JOIN t.invoice i " +
                " WHERE i.createdAt BETWEEN '"+fromDateTime+"' AND '"+toDateTime+"' " +
                " AND t.product.id = '"+productId+"' GROUP BY TransactionDate ORDER BY TransactionDate";
        Query query = entityManager.createQuery(qry);
        List<Object[]> resultList=query.getResultList();
        return resultList.stream().map(t->{

                DashboardTransactionDTO d=new DashboardTransactionDTO();
                java.sql.Date sqlDate = (java.sql.Date) t[0];
                d.setTransactionDate(sqlDate.toLocalDate());
                d.setAvgPurchasePrice( t[1]!=null?(Double) t[1]:0);
                d.setAvgSalesPrice(t[2]!=null?(Double) t[2]:0);
                return d;
    }).collect(Collectors.toList());
    }
}
