package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.Rate;
import com.example.libraryviewerbackend.model.RateIdentity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository extends BaseRepository<Rate, RateIdentity> {
    @Query("SELECT AVG(r.rateValue) FROM Rate r WHERE r.rateIdentity.ratedObjectId = :ratedObjectId AND r.rateIdentity.rateType = :rateType")
    Float findAverageRateValueByRatedObjectIdAndRateType(@Param("ratedObjectId") Integer ratedObjectId, @Param("rateType") String rateType);
}