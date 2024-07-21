package com.example.bookshop.repository;

import com.example.bookshop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCatgoryRepository extends JpaRepository<ProductCategory, Integer> {
}
