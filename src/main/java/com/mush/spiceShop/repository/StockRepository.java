package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {

}
