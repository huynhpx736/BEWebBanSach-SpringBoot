package com.example.bookshop.repository;

import com.example.bookshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    <List>Product findAllByCategory(String category);
    List<Product> findByCategoriesId(Integer categoryId);
    @Query("SELECT p FROM Product p WHERE p.title LIKE %?1%")
    List<Product> findByTitleContaining(String keyword);

    //truong hop dung sp o database tim kiem boolean
//    @Query(value = "CALL SearchProductsWithPriority :title, :titleWeight, :author, :authorWeight, " +
//            ":category, :categoryWeight, :topic, :topicWeight, :publisher, :publisherWeight, " +
//            ":publicationYear, :yearWeight, :tag, :tagWeight, :minRating, :maxRating, :ratingWeight, " +
//            ":minPrice, :maxPrice, :priceWeight, :minQuantitySold, :quantitySoldWeight, :content, :contentWeight",
//            nativeQuery = true)
//    List<Product> searchProductsWithPriority(
//            @Param("title") String title,
//            @Param("titleWeight") Float titleWeight,
//            @Param("author") String author,
//            @Param("authorWeight") Float authorWeight,
//            @Param("category") String category,
//            @Param("categoryWeight") Float categoryWeight,
//            @Param("topic") String topic,
//            @Param("topicWeight") Float topicWeight,
//            @Param("publisher") String publisher,
//            @Param("publisherWeight") Float publisherWeight,
//            @Param("publicationYear") Integer publicationYear,
//            @Param("yearWeight") Float yearWeight,
//            @Param("tag") String tag,
//            @Param("tagWeight") Float tagWeight,
//            @Param("minRating") Float minRating,
//            @Param("maxRating") Float maxRating,
//            @Param("ratingWeight") Float ratingWeight,
//            @Param("minPrice") Integer minPrice,
//            @Param("maxPrice") Integer maxPrice,
//            @Param("priceWeight") Float priceWeight,
//            @Param("minQuantitySold") Integer minQuantitySold,
//            @Param("quantitySoldWeight") Float quantitySoldWeight,
//            @Param("content") String content,
//            @Param("contentWeight") Float contentWeight
//    );
}
