package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.modelmapper.RateIdentityModelMapper;
import com.example.libraryviewerbackend.modelmapper.RateModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.RateRepositoryAdapter;
import com.openapi.gen.springboot.dto.RateDTO;
import com.openapi.gen.springboot.dto.RateIdentityDTO;
import org.springframework.stereotype.Component;

@Component
public class RateService implements IRateService {
    private final RateRepositoryAdapter rateRepositoryAdapter;

    public RateService(RateRepositoryAdapter rateRepositoryAdapter) {
        this.rateRepositoryAdapter = rateRepositoryAdapter;
    }

    @Override
    public RateDTO addRate(RateDTO rateDTO) {
        return RateModelMapper.INSTANCE.toDTO(rateRepositoryAdapter.save(RateModelMapper.INSTANCE.toEntity(rateDTO)));
    }

    @Override
    public RateDTO findRateById(RateIdentityDTO rateIdentity) {
        return RateModelMapper.INSTANCE.toDTO(rateRepositoryAdapter.findById(RateIdentityModelMapper.INSTANCE.toEntity(rateIdentity)));
    }

    @Override
    public Float getAverageRateForObject(Integer ratedObjectId, String rateType) {
        return rateRepositoryAdapter.getAverateRate(ratedObjectId, rateType);
    }
}
