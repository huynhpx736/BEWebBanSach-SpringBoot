package com.example.bookshop.repository;

import com.example.bookshop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Collection<Review> findAllByProductId(int productId);
}
