package com.example.bookshop.service.impl;

import com.example.bookshop.dto.AuthorDTO;
import com.example.bookshop.entity.Author;

import com.example.bookshop.mapper.AuthorMapper;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(int id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDTO)
                .orElse(null);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public AuthorDTO updateAuthor(int id, AuthorDTO authorDTO) {
        if (authorRepository.existsById(id)) {
            Author author = authorMapper.toEntity(authorDTO);
            author.setId(id);
            return authorMapper.toDTO(authorRepository.save(author));
        }
        return null;
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}

