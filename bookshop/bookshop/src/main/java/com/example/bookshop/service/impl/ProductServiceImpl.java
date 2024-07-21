package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ProductDTO;
import com.example.bookshop.entity.Product;
import com.example.bookshop.mapper.ProductMapper;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(int id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        if (productRepository.existsById(id)) {
            Product product = productMapper.toEntity(productDTO);
            product.setId(id);
            return productMapper.toDTO(productRepository.save(product));
        }
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
