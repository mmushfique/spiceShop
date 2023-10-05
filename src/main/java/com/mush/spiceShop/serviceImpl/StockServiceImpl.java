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
    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }
}
