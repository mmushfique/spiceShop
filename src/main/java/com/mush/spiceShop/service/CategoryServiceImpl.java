package com.mush.spiceShop.service;


import com.mush.spiceShop.domain.Category;
import com.mush.spiceShop.domain.Product;
import com.mush.spiceShop.reposiory.CategoryRepository;
import com.mush.spiceShop.reposiory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Category getCategoryById(Long categoryId){
        return categoryRepository.getById(categoryId);
    }
    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
