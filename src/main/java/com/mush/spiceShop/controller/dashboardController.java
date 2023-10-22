package com.mush.spiceShop.controller;

import com.mush.spiceShop.domain.Inventory;
import com.mush.spiceShop.domain.Person;
import com.mush.spiceShop.dto.DashboardTotalCountDTO;
import com.mush.spiceShop.dto.DashboardTransactionDTO;
import com.mush.spiceShop.repository.InvoiceRepository;
import com.mush.spiceShop.repository.PersonRepository;
import com.mush.spiceShop.repository.ProductRepository;
import com.mush.spiceShop.service.DashboardService;
import com.mush.spiceShop.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
@RequestMapping("api/dashboard")
public class dashboardController {
   @Autowired
   DashboardService dashboardService;

    @GetMapping("/totalCounts")
    public DashboardTotalCountDTO getAllTotalCounts(){

        return dashboardService.getAllTotalCounts();
    }

    @GetMapping("/transaction-by-date")
    public List<DashboardTransactionDTO> getAllTransactionsByDateAndTradeType(@RequestParam(value = "productId", required = false) Long productId){
        return dashboardService.getAllTransactionsByDateAndTradeType(productId);
    }
}
