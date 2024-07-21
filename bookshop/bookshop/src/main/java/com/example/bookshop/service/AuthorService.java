package com.example.bookshop.service;

import com.example.bookshop.dto.AuthorDTO;
import com.example.bookshop.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author>getAll();
    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(int id);
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO updateAuthor(int id, AuthorDTO authorDTO);
    void deleteAuthor(int id);
}

