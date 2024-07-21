package com.example.bookshop.service;

import com.example.bookshop.dto.ProductTagDTO;

import java.util.List;

public interface ProductTagService {
    List<ProductTagDTO> getAllProductTags();
    ProductTagDTO getProductTagById(int id);
    ProductTagDTO createProductTag(ProductTagDTO productTagDTO);
    ProductTagDTO updateProductTag(int id, ProductTagDTO productTagDTO);
    void deleteProductTag(int id);
}

