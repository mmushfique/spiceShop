package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product save(Product product);
    public Product getProductById(Long productId) ;
    public List<Product> getAllProducts();
    public void deleteProductById(Long productId);

}
