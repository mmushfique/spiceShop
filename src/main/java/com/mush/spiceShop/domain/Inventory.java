package com.mush.spiceShop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.Instant;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double weight;
    Double modifiedWeight;
    Double dryPercent;
    Double modifiedDryPercent;
    Boolean dried;
    String quality;
    Instant createdAt;
    Instant lastModifiedAt;
    @ManyToOne
    Product product;
    @OneToOne
    Transaction transaction;
}
