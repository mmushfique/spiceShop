package com.mush.spiceShop.dto;

import com.mush.spiceShop.domain.Invoice;
import com.mush.spiceShop.domain.Product;
import lombok.Data;

import java.time.Instant;
@Data
public class TransactionInputDTO {
    Long id;
    String transactionType;
    Double pricePerUnit;
    Double qty;
    Double qtyAfterDropped;
    Double price;
    Double dryPercent;
    Boolean dried;
    String quality;
    String createdBy;
    String lastModifiedBy;
    Instant createdAt;
    Instant lastModifiedAt;
    Invoice invoice;
    Product product;
}
