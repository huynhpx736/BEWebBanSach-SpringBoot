package com.example.bookshop.service.impl;

import com.example.bookshop.dto.AuthorProductDTO;
import com.example.bookshop.entity.AuthorProduct;
import com.example.bookshop.mapper.AuthorProductMapper;
import com.example.bookshop.repository.AuthorProductRepository;
import com.example.bookshop.service.AuthorProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorProductServiceImpl implements AuthorProductService {

    @Autowired
    private AuthorProductRepository authorProductRepository;

    @Autowired
    private AuthorProductMapper authorProductMapper;

    @Override
    public List<AuthorProductDTO> getAllAuthorProducts() {
        return authorProductRepository.findAll().stream()
                .map(authorProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorProductDTO getAuthorProductById(int id) {
        return authorProductRepository.findById(id)
                .map(authorProductMapper::toDTO)
                .orElse(null);
    }

    @Override
    public AuthorProductDTO createAuthorProduct(AuthorProductDTO authorProductDTO) {
        AuthorProduct authorProduct = authorProductMapper.toEntity(authorProductDTO);
        return authorProductMapper.toDTO(authorProductRepository.save(authorProduct));
    }

    @Override
    public AuthorProductDTO updateAuthorProduct(int id, AuthorProductDTO authorProductDTO) {
        if (authorProductRepository.existsById(id)) {
            AuthorProduct authorProduct = authorProductMapper.toEntity(authorProductDTO);
            authorProduct.setId(id);
            return authorProductMapper.toDTO(authorProductRepository.save(authorProduct));
        }
        return null;
    }

    @Override
    public void deleteAuthorProduct(int id) {
        authorProductRepository.deleteById(id);
    }
}

