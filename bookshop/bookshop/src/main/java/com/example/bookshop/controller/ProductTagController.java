package com.example.bookshop.controller;

import com.example.bookshop.dto.ProductTagDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-tags")
public class ProductTagController {

    @Autowired
    private ProductTagService productTagService;

    @GetMapping("get-all")
    public ResponseEntity<ResponseData> getAllProductTags() {
        List<ProductTagDTO> productTags = productTagService.getAllProductTags();
        return ResponseEntity.ok(new ResponseData(200, "Success", productTags, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getProductTagById(@PathVariable int id) {
        ProductTagDTO productTag = productTagService.getProductTagById(id);
        if (productTag != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", productTag, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "ProductTag not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createProductTag(@RequestBody ProductTagDTO productTagDTO) {
        ProductTagDTO createdProductTag = productTagService.createProductTag(productTagDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "ProductTag created", createdProductTag, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateProductTag(@PathVariable int id, @RequestBody ProductTagDTO productTagDTO) {
        ProductTagDTO updatedProductTag = productTagService.updateProductTag(id, productTagDTO);
        if (updatedProductTag != null) {
            return ResponseEntity.ok(new ResponseData(200, "ProductTag updated", updatedProductTag, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "ProductTag not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteProductTag(@PathVariable int id) {
        productTagService.deleteProductTag(id);
        return ResponseEntity.ok(new ResponseData(200, "ProductTag deleted", null, true));
    }
}
