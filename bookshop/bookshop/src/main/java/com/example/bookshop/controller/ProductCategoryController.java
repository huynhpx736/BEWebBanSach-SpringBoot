package com.example.bookshop.controller;

import com.example.bookshop.dto.ProductCategoryDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products-categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("get-all")
    public ResponseEntity<ResponseData> getAllProductCategories() {
        List<ProductCategoryDTO> productCategories = productCategoryService.getAllProductCategories();
        return ResponseEntity.ok(new ResponseData(200, "Success", productCategories, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getProductCategoryById(@PathVariable int id) {
        ProductCategoryDTO productCategory = productCategoryService.getProductCategoryById(id);
        if (productCategory != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", productCategory, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "ProductCategory not found", null, false));
        }
    }

    @PostMapping("create")
    public ResponseEntity<ResponseData> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO createdProductCategory = productCategoryService.createProductCategory(productCategoryDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "ProductCategory created", createdProductCategory, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateProductCategory(@PathVariable int id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO updatedProductCategory = productCategoryService.updateProductCategory(id, productCategoryDTO);
        if (updatedProductCategory != null) {
            return ResponseEntity.ok(new ResponseData(200, "ProductCategory updated", updatedProductCategory, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "ProductCategory not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteProductCategory(@PathVariable int id) {
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.ok(new ResponseData(200, "ProductCategory deleted", null, true));
    }
}

