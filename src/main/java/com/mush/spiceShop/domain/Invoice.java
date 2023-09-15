package com.mush.spiceShop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double total;
//    @ManyToOne
//    Supplier supplier;
//    @ManyToOne
//    Buyer buyer;
    String createdBy;
    String lastModifiedBy;
    Instant createdAt;
    Instant lastModifiedAt;
}
