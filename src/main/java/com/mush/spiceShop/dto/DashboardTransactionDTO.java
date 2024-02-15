package com.mush.spiceShop.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DashboardTransactionDTO implements Serializable {
    private LocalDate TransactionDate;
    private double AvgPurchasePrice;
    private double AvgSalesPrice;
}
