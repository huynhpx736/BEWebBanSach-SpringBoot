package com.example.bookshop.repository;

import com.example.bookshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    <List>Product findAllByCategory(String category);
    List<Product> findByCategoriesId(Integer categoryId);
}
