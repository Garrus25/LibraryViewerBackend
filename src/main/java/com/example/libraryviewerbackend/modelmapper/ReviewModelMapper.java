package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.Review;
import com.openapi.gen.springboot.dto.ReviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewModelMapper {
    ReviewModelMapper INSTANCE = Mappers.getMapper(ReviewModelMapper.class);

    ReviewDTO toDTO(Review review);

    Review toEntity(ReviewDTO reviewDTO);
}