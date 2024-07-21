package com.example.bookshop.controller;

import com.example.bookshop.dto.ReviewDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(new ResponseData(200, "Success", reviews, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getReviewById(@PathVariable int id) {
        ReviewDTO review = reviewService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", review, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Review not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Review created", createdReview, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateReview(@PathVariable int id, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        if (updatedReview != null) {
            return ResponseEntity.ok(new ResponseData(200, "Review updated", updatedReview, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Review not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(new ResponseData(200, "Review deleted", null, true));
    }
}
