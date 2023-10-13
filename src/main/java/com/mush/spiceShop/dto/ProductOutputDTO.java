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
    String category;
    String unit;
    String imageUrl;
    public ProductOutputDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.rateFrom0 = product.getRateFrom0();
        this.rateFrom50 = product.getRateFrom50();
        this.category=product.getCategory();
        this.unit = product.getUnit();
        this.imageUrl= product.getImageUrl();
    }

}
