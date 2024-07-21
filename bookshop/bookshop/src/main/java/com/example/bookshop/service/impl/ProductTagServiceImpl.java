package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ProductTagDTO;
import com.example.bookshop.entity.ProductTag;
import com.example.bookshop.mapper.ProductTagMapper;
import com.example.bookshop.repository.ProductTagRepository;
import com.example.bookshop.service.ProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTagServiceImpl implements ProductTagService {

    @Autowired
    private ProductTagRepository productTagRepository;

    @Autowired
    private ProductTagMapper productTagMapper;

    @Override
    public List<ProductTagDTO> getAllProductTags() {
        return productTagRepository.findAll().stream()
                .map(productTagMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductTagDTO getProductTagById(int id) {
        return productTagRepository.findById(id)
                .map(productTagMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ProductTagDTO createProductTag(ProductTagDTO productTagDTO) {
        ProductTag productTag = productTagMapper.toEntity(productTagDTO);
        return productTagMapper.toDTO(productTagRepository.save(productTag));
    }

    @Override
    public ProductTagDTO updateProductTag(int id, ProductTagDTO productTagDTO) {
        if (productTagRepository.existsById(id)) {
            ProductTag productTag = productTagMapper.toEntity(productTagDTO);
            productTag.setId(id);
            return productTagMapper.toDTO(productTagRepository.save(productTag));
        }
        return null;
    }

    @Override
    public void deleteProductTag(int id) {
        productTagRepository.deleteById(id);
    }
}
