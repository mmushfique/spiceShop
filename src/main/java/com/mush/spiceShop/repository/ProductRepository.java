package com.mush.spiceShop.repository;

import com.mush.spiceShop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
