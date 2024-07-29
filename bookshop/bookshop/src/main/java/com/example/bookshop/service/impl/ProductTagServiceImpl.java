package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ProductTagDTO;
import com.example.bookshop.entity.ProductTag;
import com.example.bookshop.mapper.ProductTagMapper;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.repository.ProductTagRepository;
import com.example.bookshop.repository.TagRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

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
           ProductTag entity = productTagMapper.toEntity(productTagDTO);
            if (productTagDTO.getProductId() == null || productTagDTO.getTagId() == null) return null;
            entity.setProduct(productRepository.findById(productTagDTO.getProductId()).orElse(null));
            entity.setTag(tagRepository.findById(productTagDTO.getTagId()).orElse(null));
            return productTagMapper.toDTO(productTagRepository.save(entity));
    }

    @Override
    public ProductTagDTO updateProductTag(int id, ProductTagDTO productTagDTO) {
        System.out.println(productTagDTO);
        ProductTag entity = productTagRepository.findById(id).orElse(null);
        if (entity == null) return null;
        if (productTagDTO.getProductId() == null || productTagDTO.getTagId() == null) return null;
        entity.setProduct(productRepository.findById(productTagDTO.getProductId()).orElse(null));
        entity.setTag(tagRepository.findById(productTagDTO.getTagId()).orElse(null));
        return productTagMapper.toDTO(productTagRepository.save(entity));
    }

    @Override
    public void deleteProductTag(int id) {
        productTagRepository.deleteById(id);
    }
}
