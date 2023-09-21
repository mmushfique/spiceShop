package com.mush.spiceShop.serviceImpl;


import com.mush.spiceShop.domain.Product;
import com.mush.spiceShop.repository.ProductRepository;
import com.mush.spiceShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product getProductById(Long productId){
        return productRepository.getById(productId);
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
