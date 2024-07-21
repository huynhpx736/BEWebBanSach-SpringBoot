package com.example.bookshop.repository;

import com.example.bookshop.entity.AuthorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorProductRepository extends JpaRepository<AuthorProduct, Integer> {
}
