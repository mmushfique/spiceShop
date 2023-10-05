package com.mush.spiceShop.serviceImpl;

import com.mush.spiceShop.domain.Stock;
import com.mush.spiceShop.repository.StockRepository;
import com.mush.spiceShop.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock save(Stock stock) {
        Stock existingStock=stockRepository.findStockByProductId(stock.getProduct().getId());
        if(existingStock==null){
            return stockRepository.save(stock);
        }
        existingStock.setQty(existingStock.getQty()+stock.getQty());
        return stockRepository.save(existingStock);
    }

    @Override
    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }
}
