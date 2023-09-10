package com.mush.spiceShop.service;

import com.mush.spiceShop.domain.Category;
import com.mush.spiceShop.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    public Category save(Category category);
    public Category getCategoryById(Long categoryId);
    public List<Category> getAllCategories();
    public void deleteCategoryById(Long categoryId);
}
