package com.example.bookshop.service;

import com.example.bookshop.dto.CategoryDTO;
import com.example.bookshop.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    List<Category>getall();
    CategoryDTO getCategoryById(Integer id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);
    void deleteCategory(Integer id);

}
