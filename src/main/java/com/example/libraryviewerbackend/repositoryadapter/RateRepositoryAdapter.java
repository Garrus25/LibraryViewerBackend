package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.Rate;
import com.example.libraryviewerbackend.model.RateIdentity;
import com.example.libraryviewerbackend.repository.RateRepository;
import org.springframework.stereotype.Component;

@Component
public class RateRepositoryAdapter {
    RateRepository rateRepository;

    public RateRepositoryAdapter(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public Rate save(Rate rate) {
        return rateRepository.save(rate);
    }

    public Rate findById(RateIdentity rateIdentity) {
        return rateRepository.findById(rateIdentity).orElse(null);
    }

    public Float getAverateRate(Integer ratedObjectId, String rateType) {
        return rateRepository.findAverageRateValueByRatedObjectIdAndRateType(ratedObjectId, rateType);
    }
}
