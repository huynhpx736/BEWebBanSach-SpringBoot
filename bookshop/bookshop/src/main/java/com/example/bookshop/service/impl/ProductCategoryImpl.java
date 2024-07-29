package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ProductCategoryDTO;
import com.example.bookshop.entity.Category;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.ProductCategory;
import com.example.bookshop.mapper.ProductCategoryMapper;
import com.example.bookshop.repository.CategoryRepository;
import com.example.bookshop.repository.ProductCatgoryRepository;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    @Autowired
    private ProductCatgoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
        ProductCategory entity = productCategoryMapper.toEntity(productCategoryDTO);
        Optional<Product> product = productRepository.findById(productCategoryDTO.getProductId());
        Optional<Category> category = categoryRepository.findById(productCategoryDTO.getCategoryId());
        if (product.isEmpty() || category.isEmpty()) return null;
        entity.setProduct(product.get());
        entity.setCategory(category.get());

        return productCategoryMapper.toDTO(productCategoryRepository.save(entity));
    }

    @Override
    public ProductCategoryDTO updateProductCategory( int Id,ProductCategoryDTO productCategoryDTO) {
        System.out.println(productCategoryDTO);
        if (productCategoryRepository.findById(Id).isEmpty()) return null;
        Optional<ProductCategory> prtCa = productCategoryRepository.findById(Id);
        if (prtCa.isEmpty()) return null;
        if (productCategoryDTO.getProductId() == null || productCategoryDTO.getCategoryId() == null) return null;

        Optional<Product> product = productRepository.findById(productCategoryDTO.getProductId());
        Optional<Category> category = categoryRepository.findById(productCategoryDTO.getCategoryId());
        if (product.isEmpty() || category.isEmpty()) return null;
        ProductCategory entity = productCategoryMapper.toEntity(productCategoryDTO);
        entity.setId(Id);
        entity.setProduct(product.get());
        entity.setCategory(category.get());
        return productCategoryMapper.toDTO(productCategoryRepository.save(entity));

    }

    @Override
    public void deleteProductCategory(int id) {
        productCategoryRepository.deleteById(id);
    }
}
