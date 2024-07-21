package com.example.bookshop.controller;

import com.example.bookshop.dto.AuthorProductDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.AuthorProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author-products")
public class AuthorProductController {

    @Autowired
    private AuthorProductService authorProductService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllAuthorProducts() {
        List<AuthorProductDTO> authorProducts = authorProductService.getAllAuthorProducts();
        return ResponseEntity.ok(new ResponseData(200, "Success", authorProducts, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getAuthorProductById(@PathVariable int id) {
        AuthorProductDTO authorProduct = authorProductService.getAuthorProductById(id);
        if (authorProduct != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", authorProduct, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "AuthorProduct not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createAuthorProduct(@RequestBody AuthorProductDTO authorProductDTO) {
        AuthorProductDTO createdAuthorProduct = authorProductService.createAuthorProduct(authorProductDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "AuthorProduct created", createdAuthorProduct, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateAuthorProduct(@PathVariable int id, @RequestBody AuthorProductDTO authorProductDTO) {
        AuthorProductDTO updatedAuthorProduct = authorProductService.updateAuthorProduct(id, authorProductDTO);
        if (updatedAuthorProduct != null) {
            return ResponseEntity.ok(new ResponseData(200, "AuthorProduct updated", updatedAuthorProduct, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "AuthorProduct not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteAuthorProduct(@PathVariable int id) {
        authorProductService.deleteAuthorProduct(id);
        return ResponseEntity.ok(new ResponseData(200, "AuthorProduct deleted", null, true));
    }
}
