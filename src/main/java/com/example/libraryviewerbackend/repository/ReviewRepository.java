package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewRepository extends BaseRepository<Review, Integer> {
    @Query(value = "SELECT * FROM reviews WHERE created_by = :userId", nativeQuery = true)
    List<Review> getAllReviewsCreatedBySpecificUser(String userId);
}