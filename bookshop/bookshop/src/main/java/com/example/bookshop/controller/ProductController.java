package com.example.bookshop.controller;

import com.example.bookshop.dto.ProductDTO;
import com.example.bookshop.dto.ProductSearchCriteria;
import com.example.bookshop.entity.Product;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/newest")
    public ResponseEntity<ResponseData> getNewestProducts() {
        List<ProductDTO> products = productService.getNewestProducts();
        return ResponseEntity.ok(new ResponseData(200, "Success", products, true));
    }
    @PostMapping("/BooleanSearch")
    public ResponseData searchProducts(@RequestBody ProductSearchCriteria criteria) {
        List<Product> products = productService.searchProducts(criteria);
        return new ResponseData(200, "Success", products);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseData getAllByCategoriesID(@PathVariable Integer categoryId) {
        List<ProductDTO> products = productService.getAllByCategoriesID(categoryId);
        return new ResponseData(200, "Success", products);
    }
    @GetMapping("get-all")
    public ResponseEntity<ResponseData> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(new ResponseData(200, "Success", products, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getProductById(@PathVariable int id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", product, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Product not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Product created", createdProduct, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return ResponseEntity.ok(new ResponseData(200, "Product updated", updatedProduct, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Product not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ResponseData(200, "Product deleted", null, true));
    }
}
