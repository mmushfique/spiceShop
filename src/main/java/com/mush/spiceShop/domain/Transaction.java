package com.mush.spiceShop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double pricePerUnit;
    Double qty;
    Double qtyAfterDropped;
    Double price;
    String transactionStatus;
    @ManyToOne
    Invoice invoice;
    @ManyToOne
    Product product;
}
