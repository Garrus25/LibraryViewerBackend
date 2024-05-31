package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.ReviewService;
import com.openapi.gen.springboot.api.ReviewApiController;
import com.openapi.gen.springboot.dto.ReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController extends ReviewApiController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        super(null);
        this.reviewService = reviewService;
    }

    @Override
    public ResponseEntity<ReviewDTO> addReview(ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.addReview(reviewDTO));
    }

    @Override
    public ResponseEntity<List<ReviewDTO>> getAllBooks() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @Override
    public ResponseEntity<ReviewDTO> getReviewById(Integer id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @Override
    public ResponseEntity<List<ReviewDTO>> getAllReviewsCreatedBySpecificUser(String id) {
        return ResponseEntity.ok(reviewService.getAllReviewsCreatedBySpecificUser(id));
    }
}
