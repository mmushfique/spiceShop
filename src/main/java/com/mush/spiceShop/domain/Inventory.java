package com.mush.spiceShop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double modifiedWeight;
    Double modifiedDryPercent;
    Boolean dried;
    String quality;
    String transactionStatus;
    Instant createdAt;
    Instant lastModifiedAt;
    @ManyToOne
    Product product;
}
