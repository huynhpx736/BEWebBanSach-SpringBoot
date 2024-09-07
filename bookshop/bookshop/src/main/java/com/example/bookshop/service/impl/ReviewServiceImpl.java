package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ReviewDTO;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.Review;
import com.example.bookshop.entity.User;
import com.example.bookshop.mapper.ReviewMapper;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.repository.ReviewRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ReviewDTO> getAllReviewsByBookId(int bookId) {
        return reviewRepository.findAllByProductId(bookId).stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

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
        if (reviewDTO.getProductId() == null || reviewDTO.getUserId() == null) return null;
        Optional<Product> product = productRepository.findById(reviewDTO.getProductId());
        Optional<User> user = userRepository.findById(reviewDTO.getUserId());
        if (product.isEmpty() || user.isEmpty()) return null;
        review.setProduct(product.get());
        review.setUser(user.get());

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

