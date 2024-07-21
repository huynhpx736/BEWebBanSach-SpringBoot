package com.example.bookshop.service;

import com.example.bookshop.dto.AuthorProductDTO;

import java.util.List;

public interface AuthorProductService {
    List<AuthorProductDTO> getAllAuthorProducts();
    AuthorProductDTO getAuthorProductById(int id);
    AuthorProductDTO createAuthorProduct(AuthorProductDTO authorProductDTO);
    AuthorProductDTO updateAuthorProduct(int id, AuthorProductDTO authorProductDTO);
    void deleteAuthorProduct(int id);
}

