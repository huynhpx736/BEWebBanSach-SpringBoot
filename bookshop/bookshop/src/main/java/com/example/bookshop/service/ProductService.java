package com.example.bookshop.service;//package com.example.bookshop.service;
//
//import com.example.bookshop.entity.Product;
//
//import java.util.List;
//
//public interface ProductService {
//    public List<Product> findAll();
//}
import com.example.bookshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(int id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(int id, ProductDTO productDTO);
    void deleteProduct(int id);
}
