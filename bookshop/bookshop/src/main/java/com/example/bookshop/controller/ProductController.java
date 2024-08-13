package com.example.bookshop.controller;

import com.example.bookshop.dto.ProductDTO;
import com.example.bookshop.dto.ProductSearchCriteria;
import com.example.bookshop.entity.Product;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("search-by-keyword")
    public ResponseEntity<ResponseData> getProducts(@RequestParam(required = false) String keyword) {
        List<ProductDTO> products = productService.findProducts(keyword);
        return ResponseEntity.ok(new ResponseData(200, "Success", products, true));
    }
    @PutMapping("update-status/{id}")
    public ResponseEntity<ResponseData> updateStatus(@PathVariable Integer id, @RequestParam Integer status) {
        productService.updatStatus(id, status);
        return ResponseEntity.ok(new ResponseData(200, "Status updated", null, true));
    }
    @PostMapping("update-image/{id}")
    public ResponseEntity<Product> uploadProductImage(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file) {
        try {
            // Lưu file vào hệ thống hoặc S3, sau đó lấy đường dẫn file
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            String uploadDir = "product-images/";
            String uploadDir = "D:\\Project\\React\\WebBanSach-TTTN\\bookshop-fe\\public\\images\\products";
            // Tạo đường dẫn lưu file
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu file
            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new RuntimeException("Could not save image file: " + fileName, ioe);
            }

            // Cập nhật URL của hình ảnh trong cơ sở dữ liệu
//            String imageUrl = "/product-images/" + fileName;
            String imageUrl = "/images/products/" + fileName;
            Product updatedProduct = productService.updateProductImage(id, imageUrl);

            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
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
