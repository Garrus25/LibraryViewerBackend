package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.Review;
import com.example.libraryviewerbackend.repository.ReviewRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewRepositoryAdapter {
    private final ReviewRepository reviewRepository;

    public ReviewRepositoryAdapter(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getById(Integer id) {
        return reviewRepository.findById(id);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviewsCreatedBySpecificUser(String userId) {
        return reviewRepository.getAllReviewsCreatedBySpecificUser(userId);
    }

    public List<Review> getAllReviewsByBookId(String bookId) {
        return reviewRepository.getAllByBookId(bookId);
    }
}
