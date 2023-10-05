package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Stock;
import java.util.List;

public interface StockService {
    public Stock save(Stock stock);
    public List<Stock> getAllStocks();
}
