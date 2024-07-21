package com.example.bookshop.service;

import com.example.bookshop.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDTO> getAllProductCategories();
    ProductCategoryDTO getProductCategoryById(int id);
    ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO);
    ProductCategoryDTO updateProductCategory(int id, ProductCategoryDTO productCategoryDTO);
    void deleteProductCategory(int id);
}

