package com.mush.spiceShop.controller;

import com.mush.spiceShop.domain.Invoice;
import com.mush.spiceShop.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> save(@RequestBody Invoice invoice){
            return ResponseEntity.ok(invoiceService.save(invoice));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Long invoiceId){
        Invoice invoice=invoiceService.getInvoiceById(invoiceId);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/purchase")
    public List<Invoice> getAllInvoicePurchases(@RequestParam(value = "q", required = false, defaultValue = "") String search,
                                                @RequestParam(value = "fromDateTime", required = false) Instant fromDateTime,
                                                @RequestParam(value = "toDateTime", required = false) Instant toDateTime){
        return invoiceService.getAllInvoicePurchases(search,fromDateTime, toDateTime);
    }

    @GetMapping("/sale")
    public List<Invoice> getAllInvoiceSales(@RequestParam(value = "q", required = false, defaultValue = "") String search,
                                            @RequestParam(value = "fromDateTime", required = false) Instant fromDateTime,
                                            @RequestParam(value = "toDateTime", required = false) Instant toDateTime){
        return invoiceService.getAllInvoiceSales(search,fromDateTime, toDateTime);
    }

    @PutMapping
    public ResponseEntity<Invoice> updateInvoice(Invoice invoice){
        return ResponseEntity.ok(invoiceService.save(invoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceById(@PathVariable("id") Long invoiceId){
        invoiceService.deleteInvoiceById(invoiceId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}