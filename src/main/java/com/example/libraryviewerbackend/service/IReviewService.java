package com.example.libraryviewerbackend.service;


import com.openapi.gen.springboot.dto.ReviewDTO;

import java.util.List;

public interface IReviewService {
    ReviewDTO addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getAllReviews();

    ReviewDTO getReviewById(Integer id);

    List<ReviewDTO> getAllReviewsCreatedBySpecificUser(String userId);
}
