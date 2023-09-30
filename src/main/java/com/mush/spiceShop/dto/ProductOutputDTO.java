package com.mush.spiceShop.dto;

import com.mush.spiceShop.domain.Product;
import lombok.Data;

import java.io.Serializable;
@Data
public class ProductOutputDTO implements Serializable {
    Long id;
    String name;
    String description;
    Double rateFrom0;
    Double rateFrom50;
    String unit;
    public ProductOutputDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.rateFrom0 = product.getRateFrom0();
        this.rateFrom50 = product.getRateFrom50();
        this.unit = product.getUnit();
    }

}
