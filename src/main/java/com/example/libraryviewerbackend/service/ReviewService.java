package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.exceptions.ObjectNotFoundException;
import com.example.libraryviewerbackend.model.Review;
import com.example.libraryviewerbackend.modelmapper.ReviewModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.ReviewRepositoryAdapter;
import com.openapi.gen.springboot.dto.ReviewDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ReviewService implements IReviewService {

    private final ReviewRepositoryAdapter reviewRepositoryAdapter;

    public ReviewService(ReviewRepositoryAdapter reviewRepositoryAdapter) {
        this.reviewRepositoryAdapter = reviewRepositoryAdapter;
    }

    @Override
    public ReviewDTO addReview(ReviewDTO review) {
        return ReviewModelMapper.INSTANCE.toDTO(reviewRepositoryAdapter.save(ReviewModelMapper.INSTANCE.toEntity(review)));
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepositoryAdapter.findAll().stream().map(ReviewModelMapper.INSTANCE::toDTO).toList();
    }

    @Override
    public ReviewDTO getReviewById(Integer id) {
        ReviewDTO reviewDTO = reviewRepositoryAdapter.getById(id).map(ReviewModelMapper.INSTANCE::toDTO).orElse(null);
        if (Objects.isNull(reviewDTO)) {
            throw new ObjectNotFoundException("Review with specified ID not found", Long.valueOf(id), Review.class);
        }

        return reviewDTO;
    }
}
