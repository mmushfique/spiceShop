package com.mush.spiceShop.dto;

import lombok.Data;

@Data
public class DashboardTotalCountDTO {
    int supplierCount;
    int buyerCount;
    int purchasedInvoice;
    int salesInvoice;
    double totalPurchasedAmount;
    double totalSalesAmount;


    public DashboardTotalCountDTO(int supplierCount, int buyerCount, int purchasedInvoice, int salesInvoice, double totalPurchasedAmount, double totalSalesAmount) {
        this.supplierCount = supplierCount;
        this.buyerCount = buyerCount;
        this.purchasedInvoice = purchasedInvoice;
        this.salesInvoice = salesInvoice;
        this.totalPurchasedAmount = totalPurchasedAmount;
        this.totalSalesAmount = totalSalesAmount;
    }
}
