package com.mush.spiceShop.reposiory;

import com.mush.spiceShop.domain.Category;
import com.mush.spiceShop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
