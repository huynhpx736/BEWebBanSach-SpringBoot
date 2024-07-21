package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ReviewDTO;
import com.example.bookshop.entity.Review;
import com.example.bookshop.mapper.ReviewMapper;
import com.example.bookshop.repository.ReviewRepository;
import com.example.bookshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(int id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO) {
        if (reviewRepository.existsById(id)) {
            Review review = reviewMapper.toEntity(reviewDTO);
            review.setId(id);
            return reviewMapper.toDTO(reviewRepository.save(review));
        }
        return null;
    }

    @Override
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}

