package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ProductCategoryDTO;
import com.example.bookshop.entity.ProductCategory;
import com.example.bookshop.mapper.ProductCategoryMapper;
import com.example.bookshop.repository.ProductCatgoryRepository;
import com.example.bookshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    @Autowired
    private ProductCatgoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryDTO> getAllProductCategories() {
        return productCategoryRepository.findAll().stream()
                .map(productCategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDTO getProductCategoryById(int id) {
        return productCategoryRepository.findById(id)
                .map(productCategoryMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
        return productCategoryMapper.toDTO(productCategoryRepository.save(productCategory));
    }

    @Override
    public ProductCategoryDTO updateProductCategory(int id, ProductCategoryDTO productCategoryDTO) {
        if (productCategoryRepository.existsById(id)) {
            ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO);
            productCategory.setId(id);
            return productCategoryMapper.toDTO(productCategoryRepository.save(productCategory));
        }
        return null;
    }

    @Override
    public void deleteProductCategory(int id) {
        productCategoryRepository.deleteById(id);
    }
}
