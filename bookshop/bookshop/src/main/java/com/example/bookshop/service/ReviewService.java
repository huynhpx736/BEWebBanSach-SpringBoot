package com.example.bookshop.service;
import com.example.bookshop.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getAllReviewsByBookId(int bookId);
    List<ReviewDTO> getAllReviews();
    ReviewDTO getReviewById(int id);
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(int id, ReviewDTO reviewDTO);
    void deleteReview(int id);
}

