package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.Review;
import org.springframework.stereotype.Component;

@Component
public interface ReviewRepository extends BaseRepository<Review, Integer> {

}