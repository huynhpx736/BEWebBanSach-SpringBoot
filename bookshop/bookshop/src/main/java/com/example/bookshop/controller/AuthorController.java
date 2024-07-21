package com.example.bookshop.controller;

import com.example.bookshop.dto.AuthorDTO;
import com.example.bookshop.entity.Author;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @GetMapping("getall")
    public ResponseEntity<ResponseData> getAll() {
        List<Author> authors = authorService.getAll();
        return ResponseEntity.ok(new ResponseData(200, "Success", authors, true));
    }
    @GetMapping("get-all")
    public ResponseEntity<ResponseData> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(new ResponseData(200, "Success", authors, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getAuthorById(@PathVariable int id) {
        AuthorDTO author = authorService.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", author, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Author not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Author created", createdAuthor, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateAuthor(@PathVariable int id, @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
        if (updatedAuthor != null) {
            return ResponseEntity.ok(new ResponseData(200, "Author updated", updatedAuthor, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Author not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(new ResponseData(200, "Author deleted", null, true));
    }
}
