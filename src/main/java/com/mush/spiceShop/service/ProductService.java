package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Product;

import java.util.List;

public interface ProductService {
    public Product save(Product product);
    public Product getProductById(Long productId) ;
    public Product getDriedProduct(Product wetProduct);
    public List<Product> getAllProducts();
    public void deleteProductById(Long productId);

}
